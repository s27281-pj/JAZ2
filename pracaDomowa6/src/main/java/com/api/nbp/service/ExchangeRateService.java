package com.api.nbp.service;

import com.api.nbp.client.NbpClient;
import com.api.nbp.client.NbpClient.NbpResponse;
import com.api.nbp.dto.ExchangeRateResponse;
import com.api.nbp.entity.ExchangeRateQuery;
import com.api.nbp.exception.InvalidRequestException;
import com.api.nbp.repository.ExchangeRateQueryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class ExchangeRateService {

    private final NbpClient nbpClient;
    private final ObjectMapper objectMapper;
    private final ExchangeRateQueryRepository exchangeRateQueryRepository;

    public ExchangeRateService(
            NbpClient nbpClient,
            ObjectMapper objectMapper,
            ExchangeRateQueryRepository exchangeRateQueryRepository
    ) {
        this.nbpClient = nbpClient;
        this.objectMapper = objectMapper;
        this.exchangeRateQueryRepository = exchangeRateQueryRepository;
    }

    @Transactional
    public ExchangeRateResponse calculateAverageRate(String currency, int days) {
        if (currency == null || currency.isBlank()) {
            throw new InvalidRequestException("Currency is required");
        }
        if (days < 1) {
            throw new InvalidRequestException("Days must be greater than 0");
        }
        if (days > 255) {
            throw new InvalidRequestException("NBP API supports up to 255 last quotations");
        }

        String normalizedCurrency = currency.trim().toUpperCase();
        String responseJson = nbpClient.getAverageRatesJson(normalizedCurrency, days);
        NbpResponse nbpResponse = parseResponse(responseJson);

        if (nbpResponse.rates() == null || nbpResponse.rates().isEmpty()) {
            throw new InvalidRequestException("NBP API returned no rates");
        }

        BigDecimal sum = nbpResponse.rates().stream()
                .map(rate -> rate.mid())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averageRate = sum.divide(BigDecimal.valueOf(nbpResponse.rates().size()), 6, RoundingMode.HALF_UP);

        ExchangeRateQuery savedQuery = exchangeRateQueryRepository.save(
                new ExchangeRateQuery(normalizedCurrency, days, averageRate, LocalDateTime.now())
        );

        return new ExchangeRateResponse(
                savedQuery.getId(),
                savedQuery.getCurrency(),
                savedQuery.getDays(),
                savedQuery.getAverageRate(),
                savedQuery.getRequestedAt()
        );
    }

    private NbpResponse parseResponse(String responseJson) {
        try {
            return objectMapper.readValue(responseJson, NbpResponse.class);
        } catch (JsonProcessingException exception) {
            throw new InvalidRequestException("Could not parse NBP API response");
        }
    }
}

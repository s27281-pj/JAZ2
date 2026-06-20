package com.api.nbp.controller;

import com.api.nbp.dto.ExchangeRateResponse;
import com.api.nbp.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Tag(name = "Exchange rates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/api/exchange-rates/{currency}")
    @Operation(
            summary = "Wylicza sredni kurs waluty",
            description = "Zwraca sredni kurs waluty z przedzialu dat YYYY-MM-DD - YYYY-MM-DD na podstawie API NBP i zapisuje zapytanie w bazie danych."
    )
    @ApiResponse(responseCode = "200", description = "Sredni kurs zostal wyliczony")
    @ApiResponse(responseCode = "400", description = "Niepoprawne parametry lub blad 400 z NBP")
    @ApiResponse(responseCode = "404", description = "Waluta nie zostala znaleziona w NBP")
    @ApiResponse(responseCode = "429", description = "Przekroczony limit zapytan NBP")
    @ApiResponse(responseCode = "503", description = "NBP API jest niedostepne")
    public ExchangeRateResponse getAverageExchangeRate(
            @Parameter(description = "Kod waluty, np. EUR, USD, CHF")
            @PathVariable String currency,
            @Parameter(description = "Poczatek zakresu dat w formacie YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "Koniec zakresu dat w formacie YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return exchangeRateService.calculateAverageRate(currency, startDate, endDate);
    }
}

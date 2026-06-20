package com.api.nbp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExchangeRateResponse(
        Long id,
        String currency,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal averageRate,
        LocalDateTime requestedAt
) {
}

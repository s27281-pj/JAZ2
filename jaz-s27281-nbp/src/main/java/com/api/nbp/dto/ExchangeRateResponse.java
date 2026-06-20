package com.api.nbp.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExchangeRateResponse(
        Long id,
        String currency,
        int days,
        BigDecimal averageRate,
        LocalDateTime requestedAt
) {
}

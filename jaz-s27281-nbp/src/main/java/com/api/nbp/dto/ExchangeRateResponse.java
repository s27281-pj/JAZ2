package com.api.nbp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Odpowiedz z obliczonym srednim kursem waluty i danymi zapisanego zapytania")
public record ExchangeRateResponse(
        @Schema(description = "Identyfikator zapisanego zapytania w bazie H2", example = "1")
        Long id,
        @Schema(description = "Kod waluty", example = "EUR")
        String currency,
        @Schema(description = "Data poczatkowa zakresu", example = "2024-01-02")
        LocalDate startDate,
        @Schema(description = "Data koncowa zakresu", example = "2024-01-05")
        LocalDate endDate,
        @Schema(description = "Obliczony sredni kurs waluty", example = "4.352225")
        BigDecimal averageRate,
        @Schema(description = "Data i godzina wykonania oraz zapisania zapytania", example = "2026-06-20T10:24:19.2128103")
        LocalDateTime requestedAt
) {
}

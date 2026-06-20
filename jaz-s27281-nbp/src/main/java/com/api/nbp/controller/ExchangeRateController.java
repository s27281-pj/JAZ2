package com.api.nbp.controller;

import com.api.nbp.dto.ExchangeRateResponse;
import com.api.nbp.dto.ErrorResponse;
import com.api.nbp.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
            description = "Zwraca sredni kurs waluty z przedzialu dat YYYY-MM-DD - YYYY-MM-DD na podstawie API NBP i zapisuje kazde poprawne zapytanie w bazie H2. Zakres dat nie moze byc dluzszy niz 93 dni, a endDate nie moze byc data z przyszlosci."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Sredni kurs zostal wyliczony i zapytanie zostalo zapisane w H2",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExchangeRateResponse.class),
                    examples = @ExampleObject(value = """
                            {
                              "id": 1,
                              "currency": "EUR",
                              "startDate": "2024-01-02",
                              "endDate": "2024-01-05",
                              "averageRate": 4.352225,
                              "requestedAt": "2026-06-20T10:24:19.2128103"
                            }
                            """)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "Niepoprawne parametry, niepoprawny format daty, zakres dluzszy niz 93 dni lub blad 400 z NBP",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "NBP nie zwrocilo danych dla podanej waluty i zakresu dat",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
            responseCode = "429",
            description = "Przekroczony limit zapytan NBP",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    )
    @ApiResponse(
            responseCode = "503",
            description = "NBP API jest niedostepne",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    )
    public ExchangeRateResponse getAverageExchangeRate(
            @Parameter(description = "Kod waluty w standardzie ISO 4217", example = "EUR", required = true)
            @PathVariable String currency,
            @Parameter(description = "Poczatek zakresu dat w formacie YYYY-MM-DD", example = "2024-01-02", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "Koniec zakresu dat w formacie YYYY-MM-DD", example = "2024-01-05", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        return exchangeRateService.calculateAverageRate(currency, startDate, endDate);
    }
}

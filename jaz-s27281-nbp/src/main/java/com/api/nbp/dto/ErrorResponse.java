package com.api.nbp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Odpowiedz zwracana w przypadku bledu")
public record ErrorResponse(
        @Schema(description = "Kod statusu HTTP", example = "400")
        int status,
        @Schema(description = "Nazwa statusu HTTP", example = "Bad Request")
        String error,
        @Schema(description = "Opis bledu", example = "Date range cannot be longer than 93 days")
        String message,
        @Schema(description = "Data i godzina wystapienia bledu", example = "2026-06-20T10:42:19.2128103")
        LocalDateTime timestamp
) {
}

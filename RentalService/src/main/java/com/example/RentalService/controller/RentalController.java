package com.example.RentalService.controller;

import com.example.RentalService.model.Movie;
import com.example.RentalService.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "RentalService", description = "Operacje wypożyczania i oddawania filmów")
@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Operation(
            summary = "Pobierz film przez RentalService",
            description = "Pobiera film z MovieService po jego identyfikatorze."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film znaleziony"),
            @ApiResponse(responseCode = "400", description = "Niepoprawne żądanie do MovieService"),
            @ApiResponse(responseCode = "404", description = "Film nie istnieje"),
            @ApiResponse(responseCode = "502", description = "MovieService zwrócił błąd 500"),
            @ApiResponse(responseCode = "504", description = "Brak połączenia z MovieService")
    })
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(
            @Parameter(description = "ID filmu", example = "1") @PathVariable Long id) {

        Optional<Movie> movie = rentalService.getMovie(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Oddaj film",
            description = "Wywołuje MovieService i ustawia film jako dostępny."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film został oznaczony jako dostępny"),
            @ApiResponse(responseCode = "400", description = "Niepoprawne żądanie do MovieService"),
            @ApiResponse(responseCode = "404", description = "Film nie istnieje"),
            @ApiResponse(responseCode = "502", description = "MovieService zwrócił błąd 500"),
            @ApiResponse(responseCode = "504", description = "Brak połączenia z MovieService")
    })
    @PatchMapping("/movies/{id}/return")
    public ResponseEntity<Movie> returnMovie(
            @Parameter(description = "ID filmu", example = "1") @PathVariable Long id) {

        Optional<Movie> movie = rentalService.returnMovie(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Wypożycz film",
            description = "Wywołuje MovieService i ustawia film jako niedostępny."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film został oznaczony jako niedostępny"),
            @ApiResponse(responseCode = "400", description = "Niepoprawne żądanie do MovieService"),
            @ApiResponse(responseCode = "404", description = "Film nie istnieje"),
            @ApiResponse(responseCode = "502", description = "MovieService zwrócił błąd 500"),
            @ApiResponse(responseCode = "504", description = "Brak połączenia z MovieService")
    })
    @PatchMapping("/movies/{id}/rent")
    public ResponseEntity<Movie> rentMovie(
            @Parameter(description = "ID filmu", example = "1") @PathVariable Long id) {

        Optional<Movie> movie = rentalService.rentMovie(id);
        return movie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
package com.example.MovieService.controller;

import com.example.MovieService.model.Movie;
import com.example.MovieService.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "MovieService", description = "Operacje na filmach i ich dostępności")
@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(
            summary = "Pobierz wszystkie filmy",
            description = "Zwraca listę wszystkich filmów zapisanych w bazie danych."
    )
    @ApiResponse(responseCode = "200", description = "Lista filmów zwrócona poprawnie")
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @Operation(
            summary = "Pobierz film po ID",
            description = "Zwraca pojedynczy film o podanym identyfikatorze."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film znaleziony"),
            @ApiResponse(responseCode = "404", description = "Film nie istnieje")
    })
    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovieById(
            @Parameter(description = "ID filmu", example = "1") @PathVariable Long id) {

        Optional<Movie> movie = movieService.getMovieById(id);

        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Dodaj nowy film",
            description = "Tworzy nowy rekord filmu."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film dodany poprawnie"),
            @ApiResponse(responseCode = "400", description = "Niepoprawne dane wejściowe")
    })
    @PostMapping("/movies")
    public ResponseEntity<Movie> addMovie(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dane nowego filmu",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Przykład filmu",
                                    value = """
                                            {
                                              "name": "The Matrix",
                                              "category": "Sci-Fi",
                                              "director": "The Wachowskis",
                                              "releaseYear": 1999,
                                              "available": false
                                            }
                                            """
                            )
                    )
            )
            @RequestBody Movie movie) {
        try {
            Movie createdMovie = movieService.addMovie(movie);
            return ResponseEntity.ok(createdMovie);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Zaktualizuj film",
            description = "Aktualizuje dane istniejącego filmu."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Film zaktualizowany"),
            @ApiResponse(responseCode = "400", description = "Niepoprawne dane wejściowe"),
            @ApiResponse(responseCode = "404", description = "Film nie istnieje")
    })
    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(
            @Parameter(description = "ID filmu", example = "1") @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nowe dane filmu",
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Przykład aktualizacji",
                                    value = """
                                            {
                                              "name": "Blade Runner Final Cut",
                                              "category": "Sci-Fi",
                                              "director": "Ridley Scott",
                                              "releaseYear": 1982,
                                              "available": true
                                            }
                                            """
                            )
                    )
            )
            @RequestBody Movie movie) {
        try {
            Optional<Movie> updatedMovie = movieService.updateMovie(id, movie);

            if (updatedMovie.isPresent()) {
                return ResponseEntity.ok(updatedMovie.get());
            }

            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(
            summary = "Usuń film",
            description = "Usuwa film o podanym identyfikatorze."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Film usunięty"),
            @ApiResponse(responseCode = "404", description = "Film nie istnieje")
    })
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(
            @Parameter(description = "ID filmu", example = "1") @PathVariable Long id) {

        boolean deleted = movieService.deleteMovie(id);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Oznacz film jako dostępny",
            description = "Ustawia pole available na true."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status zmieniony na dostępny"),
            @ApiResponse(responseCode = "404", description = "Film nie istnieje")
    })
    @PatchMapping("/movies/{id}/available")
    public ResponseEntity<Movie> makeMovieAvailable(
            @Parameter(description = "ID filmu", example = "1") @PathVariable Long id) {

        Optional<Movie> updatedMovie = movieService.makeAvailable(id);
        return updatedMovie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Oznacz film jako niedostępny",
            description = "Ustawia pole available na false."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status zmieniony na niedostępny"),
            @ApiResponse(responseCode = "404", description = "Film nie istnieje")
    })
    @PatchMapping("/movies/{id}/unavailable")
    public ResponseEntity<Movie> makeMovieUnavailable(
            @Parameter(description = "ID filmu", example = "1") @PathVariable Long id) {

        Optional<Movie> updatedMovie = movieService.makeUnavailable(id);
        return updatedMovie.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
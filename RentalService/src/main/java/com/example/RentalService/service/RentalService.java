package com.example.RentalService.service;

import com.example.RentalService.model.Movie;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.ConnectException;
import java.util.Optional;

@Service
public class RentalService {

    private static final String MOVIE_SERVICE_URL = "http://localhost:8080/movies";

    private final RestTemplate restTemplate;

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Movie> getMovie(Long id) {
        try {
            Movie movie = restTemplate.getForObject(
                    MOVIE_SERVICE_URL + "/{id}",
                    Movie.class,
                    id
            );
            return Optional.ofNullable(movie);
        } catch (Exception e) {
            handleMovieServiceException(e);
            return Optional.empty();
        }
    }

    public Optional<Movie> returnMovie(Long id) {
        try {
            ResponseEntity<Movie> response = restTemplate.exchange(
                    MOVIE_SERVICE_URL + "/{id}/available",
                    HttpMethod.PATCH,
                    HttpEntity.EMPTY,
                    Movie.class,
                    id
            );
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            handleMovieServiceException(e);
            return Optional.empty();
        }
    }

    public Optional<Movie> rentMovie(Long id) {
        try {
            ResponseEntity<Movie> response = restTemplate.exchange(
                    MOVIE_SERVICE_URL + "/{id}/unavailable",
                    HttpMethod.PATCH,
                    HttpEntity.EMPTY,
                    Movie.class,
                    id
            );
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            handleMovieServiceException(e);
            return Optional.empty();
        }
    }

    private void handleMovieServiceException(Exception e) {
        if (e instanceof HttpClientErrorException.NotFound) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }

        if (e instanceof HttpClientErrorException.BadRequest) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request to MovieService");
        }

        if (e instanceof HttpServerErrorException.InternalServerError) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "MovieService returned 500");
        }

        if (e instanceof ResourceAccessException && e.getCause() instanceof ConnectException) {
            throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "MovieService is unavailable");
        }

        throw new RuntimeException(e);
    }
}
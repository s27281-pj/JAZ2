package com.example.MovieService.service;

import com.example.MovieService.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieService {

    private final List<Movie> movies = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public MovieService() {
        movies.add(new Movie(nextId.getAndIncrement(), "Blade Runner", "Sci-Fi", "Ridley Scott", 1982));
        movies.add(new Movie(nextId.getAndIncrement(), "Alien", "Sci-Fi", "Ridley Scott", 1979));
        movies.add(new Movie(nextId.getAndIncrement(), "The Godfather", "Crime", "Francis Ford Coppola", 1972));
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public Optional<Movie> getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst();
    }

    public Movie addMovie(Movie movie) {
        validateMovie(movie);

        Movie newMovie = new Movie(
                nextId.getAndIncrement(),
                movie.getName(),
                movie.getCategory(),
                movie.getDirector(),
                movie.getReleaseYear()
        );

        movies.add(newMovie);
        return newMovie;
    }

    public Optional<Movie> updateMovie(Long id, Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }

        Optional<Movie> existingMovieOptional = getMovieById(id);

        if (existingMovieOptional.isEmpty()) {
            return Optional.empty();
        }

        Movie existingMovie = existingMovieOptional.get();
        existingMovie.setName(movie.getName());
        existingMovie.setCategory(movie.getCategory());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setReleaseYear(movie.getReleaseYear());

        return Optional.of(existingMovie);
    }

    public boolean deleteMovie(Long id) {
        return movies.removeIf(movie -> movie.getId().equals(id));
    }

    private void validateMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }

        if (movie.getId() != null) {
            throw new IllegalArgumentException("Id should not be provided");
        }

        if (movie.getName() == null || movie.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (movie.getCategory() == null || movie.getCategory().isBlank()) {
            throw new IllegalArgumentException("Category is required");
        }
    }
}
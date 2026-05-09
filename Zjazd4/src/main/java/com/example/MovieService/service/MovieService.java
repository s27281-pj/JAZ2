package com.example.MovieService.service;

import com.example.MovieService.model.Movie;
import com.example.MovieService.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie addMovie(Movie movie) {
        validateMovie(movie);
        return movieRepository.save(movie);
    }

    public Optional<Movie> updateMovie(Long id, Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }

        Optional<Movie> existingMovieOptional = movieRepository.findById(id);

        if (existingMovieOptional.isEmpty()) {
            return Optional.empty();
        }

        Movie existingMovie = existingMovieOptional.get();
        existingMovie.setName(movie.getName());
        existingMovie.setCategory(movie.getCategory());
        existingMovie.setDirector(movie.getDirector());
        existingMovie.setReleaseYear(movie.getReleaseYear());
        existingMovie.setAvailable(movie.isAvailable());

        return Optional.of(movieRepository.save(existingMovie));
    }

    public boolean deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            return false;
        }

        movieRepository.deleteById(id);
        return true;
    }

    public Optional<Movie> makeAvailable(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (movieOptional.isEmpty()) {
            return Optional.empty();
        }

        Movie movie = movieOptional.get();
        movie.setAvailable(true);

        return Optional.of(movieRepository.save(movie));
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
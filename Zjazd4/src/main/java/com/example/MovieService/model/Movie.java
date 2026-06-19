package com.example.MovieService.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "movie")
@NamedQueries({
        @NamedQuery(
                name = "Movie.findAll",
                query = "SELECT m FROM Movie m"
        ),
        @NamedQuery(
                name = "Movie.findById",
                query = "SELECT m FROM Movie m WHERE m.id = :id"
        ),
        @NamedQuery(
                name = "Movie.existsById",
                query = "SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM Movie m WHERE m.id = :id"
        )
})
@Schema(
        name = "Movie",
        description = "Model filmu przechowywany w MovieService. Zawiera podstawowe informacje o filmie oraz jego dostępności."
)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unikalny identyfikator filmu", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "Nazwa filmu", example = "Blade Runner")
    private String name;

    @Column(nullable = false)
    @Schema(description = "Kategoria filmu", example = "Sci-Fi")
    private String category;

    @Schema(description = "Reżyser filmu", example = "Ridley Scott")
    private String director;

    @Column(name = "release_year")
    @Schema(description = "Rok wydania filmu", example = "1982")
    private Integer releaseYear;

    @Column(name = "is_available", nullable = false)
    @Schema(description = "Informacja, czy film jest dostępny", example = "true")
    private boolean isAvailable;

    public Movie() {
    }

    public Movie(Long id, String name, String category, String director, Integer releaseYear, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.director = director;
        this.releaseYear = releaseYear;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
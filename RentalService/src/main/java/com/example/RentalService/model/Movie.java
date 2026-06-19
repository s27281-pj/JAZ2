package com.example.RentalService.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Movie",
        description = "Model filmu używany w RentalService do komunikacji z MovieService."
)
public class Movie {

    @Schema(description = "Unikalny identyfikator filmu", example = "1")
    private Long id;

    @Schema(description = "Nazwa filmu", example = "Blade Runner")
    private String name;

    @Schema(description = "Kategoria filmu", example = "Sci-Fi")
    private String category;

    @Schema(description = "Reżyser filmu", example = "Ridley Scott")
    private String director;

    @Schema(description = "Rok wydania filmu", example = "1982")
    private Integer releaseYear;

    @Schema(description = "Informacja, czy film jest dostępny", example = "true")
    private boolean available;

    public Movie() {
    }

    public Movie(Long id, String name, String category, String director, Integer releaseYear, boolean available) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.director = director;
        this.releaseYear = releaseYear;
        this.available = available;
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
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
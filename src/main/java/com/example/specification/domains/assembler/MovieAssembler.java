package com.example.specification.domains.assembler;

import com.example.specification.domains.Movie;
import com.example.specification.domains.dto.MovieDTO;
import org.springframework.stereotype.Component;

@Component
public class MovieAssembler extends AbstractAssembler<MovieDTO, Movie> {
    @Override
    public MovieDTO assembleDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setRating(movie.getRating());
        dto.setGenre(movie.getGenre());
        dto.setTitle(movie.getTitle());
        dto.setReleaseYear(movie.getReleaseYear());
        dto.setWatchTime(movie.getWatchTime());
        return dto;
    }

    @Override
    public Movie assembleEntity(MovieDTO dto) {
        return assembleEntity(dto, new Movie());
    }

    @Override
    public Movie assembleEntity(MovieDTO dto, Movie movie) {
        movie.setId(dto.getId());
        movie.setGenre(dto.getGenre());
        movie.setTitle(dto.getTitle());
        movie.setRating(dto.getRating());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setWatchTime(dto.getWatchTime());
        return movie;
    }
}
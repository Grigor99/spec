package com.example.specification.service.impl;

import com.example.specification.domains.Movie;
import com.example.specification.exceptions.NotFoundException;
import com.example.specification.repositories.MovieRepository;
import com.example.specification.service.abst.MovieService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class MovieServiceImplTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;

    @Test
    @DisplayName("testing get by title")
    void getByTitle() {
        //given
        Movie movie1 = new Movie(null, "Avatar", "action", 5.0, 199D, 1999);
        Movie movie2 = new Movie(null, "Jocker", "action2", 5.0, 199D, 1999);
        movieRepository.saveAll(Arrays.asList(movie2, movie1));
        //when
        List<Movie> movieList1 = movieService.getByTitle("Avatar");
        List<Movie> movieList2 = movieService.getByTitle("Jocker");
        //then
        movieList1.forEach(m -> assertThat(m.getId()).isNotNull());
        movieList2.forEach(m -> assertThat(m.getId()).isNotNull());
        assertThat(movieList1)
                .isNotEmpty()
                .hasSizeBetween(1, 1);
        assertThat(movieList2)
                .isNotEmpty()
                .hasSizeBetween(1, 1);
    }

    @Test
    void getById_whenNoMovieThrows_NotFoundException() {
        //given
        Long id = 123L;
        //when
        Throwable throwable = catchThrowable(() -> movieService.findById(id));
        //then
        BDDAssertions.then(throwable).isInstanceOf(NotFoundException.class).hasMessageContaining("movie.not.found");
    }
}
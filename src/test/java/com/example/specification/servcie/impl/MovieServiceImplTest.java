package com.example.specification.servcie.impl;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.MovieRepository;
import com.example.specification.servcie.abst.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
class MovieServiceImplTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Test
    void getByTitle() {

        //given
        Movie movie1 = new Movie(null, "Avatar", "action", 5.0, 199D, 1999);
        Movie movie2 = new Movie(null, "Jocker", "action2", 5.0, 199D, 1999);
        Movie movie3 = new Movie(null, "Jocker", "action2actio", 3.0, 1D, 1999);

        movieRepository.saveAll(Arrays.asList(movie2, movie1,movie3));
        //when

        List<Movie> movieList1 = movieService.getByTitle("Avatar");
        List<Movie> movieList2 = movieService.getByTitle("Jocker");

        //then
        movieList1.forEach(m->assertThat(m.getId()).isNotNull());
        movieList2.forEach(m->assertThat(m.getId()).isNotNull());
        assertThat(movieList1)
                .isNotEmpty()
                .hasSizeBetween(1,1);
        assertThat(movieList2)
                .isNotEmpty()
                .hasSizeBetween(1,2);
    }
}
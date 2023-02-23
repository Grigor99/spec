package com.example.specification.service.impl;

import com.example.specification.concurrent.ConcurrentExecution;
import com.example.specification.domains.Movie;
import com.example.specification.repositories.MovieRepository;
import com.example.specification.service.abst.MovieService;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MovieCacheServiceImpl implements ConcurrentExecution {
    @Autowired
    private MovieService movieService;
    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void getMovieByIdFromCache() {
        //given
        Long id = 123L;
        Movie movie = Instancio.create(Movie.class);

        given(movieRepository.findById(anyLong())).willReturn(Optional.of(movie));
        //when
        movieService.findById(id);
        movieService.findById(id);
        movieService.findById(id);
        //then
        then(movieRepository).should(atMostOnce()).findById(id);
    }
}
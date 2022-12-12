package com.example.specification.service.impl;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.MovieRepository;
import com.example.specification.service.abst.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MovieCacheServiceImpl {
    @Autowired
    private MovieService movieService;
    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void getMovieByIdFromCache() {
        //given
        Long id = 123L;
        given(movieRepository.findById(id)).willReturn(Optional.of(new Movie(id, "Avatar", "Action", 1222D, 122D, 1999)));
        //when
        movieService.findById(id);
        movieService.findById(id);
        movieService.findById(id);
        //then
        then(movieRepository).should(atMostOnce()).findById(id);
    }
}

package com.example.specification.service.impl;

import com.example.specification.concurrent.ConcurrentExecution;
import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComment;
import com.example.specification.domains.parametrs.RankCommentArgumentProvider;
import com.example.specification.exceptions.NotFoundException;
import com.example.specification.repositories.MovieRepository;
import com.example.specification.service.abst.MovieService;
import lombok.RequiredArgsConstructor;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.instancio.Select.field;

@Transactional
@RequiredArgsConstructor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MovieServiceImplTest implements ConcurrentExecution {
    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @Test
    @DisplayName("testing get by title")
    void getByTitle() {
        //given
        Movie m1 = Instancio.of(Movie.class)
                .set(field(Movie::getTitle), "Avatar")
                .create();
        movieRepository.saveAll(Arrays.asList(m1));
        //when
        List<Movie> movieList1 = movieService.getByTitle(m1.getTitle());
        //then
        then(movieList1).isNotEmpty();
        then(movieList1.get(0).getTitle()).isEqualTo("Avatar");
    }

    @Test
    void getById_whenNoMovieThrows_NotFoundException() {
        //given
        Long id = 123L;
        //when
        Throwable throwable = catchThrowable(() -> movieService.findById(id));
        //then
        then(throwable).isInstanceOf(NotFoundException.class).hasMessageContaining("movie.not.found");
    }

    @ParameterizedTest
    @ArgumentsSource(RankCommentArgumentProvider.class)
    void findByJoin(double number, String comment) {
        //given
        Set<MovieComment> movieCommentSet = Instancio.ofSet(MovieComment.class).size(3).create();
        Movie movie = Instancio.of(Movie.class)
                .set(field(Movie::getMovieComments), movieCommentSet)
                .create();
        movieCommentSet.forEach(m -> m.setMovie(movie));
        movieRepository.save(movie);
        //when
        List<Movie> list = movieService.findByJoin(number, comment, "", "");
        //then
        then(list).isNotEmpty().hasSizeBetween(1, 1);
    }
}
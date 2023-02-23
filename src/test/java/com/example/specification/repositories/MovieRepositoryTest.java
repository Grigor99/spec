package com.example.specification.repositories;

import com.example.specification.concurrent.ConcurrentExecution;
import com.example.specification.domains.Movie;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;
import static org.instancio.Select.field;

@DataJpaTest
class MovieRepositoryTest implements ConcurrentExecution {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("test to see if getByTitle returns correct value")
    void getByTitle() {
        //given
        Movie m1 = Instancio.of(Movie.class)
                .set(field(Movie::getTitle), "Avatar")
                .ignore(field(Movie::getId))
                .ignore(field(Movie::getMovieComments))
                .create();
        Movie m2 = Instancio.of(Movie.class)
                .set(field(Movie::getTitle), "Avatar")
                .ignore(field(Movie::getId))
                .ignore(field(Movie::getMovieComments))
                .create();
        Movie persisted1 = testEntityManager.persistFlushFind(m1);
        Movie persisted2 = testEntityManager.persistFlushFind(m2);
        List<Movie> persisted = new ArrayList<>();
        persisted.add(persisted1);
        persisted.add(persisted2);
        //when
        List<Movie> list = movieRepository.getByTitle("Avatar");
        then(m1.getId()).isNotNull();
        //then
        then(m2.getId()).isNotNull();
        then(list).isEqualTo(persisted);
    }

    @Test
    @DisplayName("test to see if rating between 2 numbers works correctly")
    void findByRatingBetween() {
        //given
        Movie m1 = Instancio.of(Movie.class)
                .set(field(Movie::getTitle), "Avatar")
                .set(field(Movie::getRating), 2D)
                .ignore(field(Movie::getId))
                .ignore(field(Movie::getMovieComments))
                .create();
        Movie m2 = Instancio.of(Movie.class)
                .set(field(Movie::getTitle), "Terminator")
                .set(field(Movie::getRating), 4D)
                .ignore(field(Movie::getId))
                .ignore(field(Movie::getMovieComments))
                .create();
        Movie persisted1 = testEntityManager.persistFlushFind(m1);
        Movie persisted2 = testEntityManager.persistFlushFind(m2);
        List<Movie> persisted = new ArrayList<>();
        persisted.add(persisted1);
        persisted.add(persisted2);
        //when
        List<Movie> fromDb = movieRepository.findByRatingBetween(2d, 5d);
        //then
        then(fromDb.size()).isEqualTo(2);
        then(persisted.size()).isEqualTo(fromDb.size());
    }
}
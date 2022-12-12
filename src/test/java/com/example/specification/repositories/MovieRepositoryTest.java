package com.example.specification.repositories;

import com.example.specification.domains.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@Execution(ExecutionMode.CONCURRENT)
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @DisplayName("test to see if getByTitle returns correct value")
    void getByTitle() {
        //given
        Movie movie1 = new Movie(null, "name", "action", 5.0, 199D, 1999);
        Movie movie2 = new Movie(null, "name", "action2", 5.0, 199D, 1999);
        Movie persisted1 = testEntityManager.persistFlushFind(movie1);
        Movie persisted2 = testEntityManager.persistFlushFind(movie2);
        List<Movie> persisted = new ArrayList<>();
        persisted.add(persisted1);
        persisted.add(persisted2);
        //when
        List<Movie> list = movieRepository.getByTitle("name");
        then(movie1.getId()).isNotNull();
        //then
        then(movie2.getId()).isNotNull();
        then(list).isEqualTo(persisted);
    }

    @Test
    @DisplayName("test to see if rating between 2 numbers works correctly")
    void findByRatingBetween() {
        //given
        Movie avatar = new Movie(null, "avatar", "action", 4.0, 199D, 1999);
        Movie terminator = new Movie(null, "terminator", "action2", 3.0, 199D, 1999);
        Movie persisted1 = testEntityManager.persistFlushFind(avatar);
        Movie persisted2 = testEntityManager.persistFlushFind(terminator);
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
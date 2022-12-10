package com.example.specification.repositories.custom;

import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComment;
import com.example.specification.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Execution(ExecutionMode.CONCURRENT)
class MovieCustomRepositoryImplTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void findByJoin() {
//        MovieComment movieComment = new MovieComment();
//        movieComment.setId(1L);
//        movieComment.setComment("good movie");
//        movieComment.setRank(5);
//
//        Movie movie1 = new Movie(null, "name", "action", 5.0, 199D, 1999, Collections.singleton(movieComment));
//        Movie movie2 = new Movie(null, "name", "action2", 5.0, 199D, 1999, Collections.singleton(movieComment));
//        Iterable<Movie> persisted = movieRepository.saveAll(Arrays.asList(movie1, movie2));
//        Iterable<Movie> iterable = testEntityManager.persistFlushFind(persisted);
//        //when
//        List<Movie> list = movieRepository.findByJoin(4d, "good movie");
//        then(movie1.getId()).isNotNull();
//        //then
//        then(movie2.getId()).isNotNull();
//        then(list).isEqualTo(iterable);


    }
}
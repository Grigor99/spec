package com.example.specification.repositories.custom;

import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComment;
import com.example.specification.domains.parametrs.RankCommentArgumentProvider;
import com.example.specification.repositories.MovieRepository;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@Execution(ExecutionMode.CONCURRENT)
class MovieCustomRepositoryImplTest {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    TestEntityManager testEntityManager;
    //    @ParameterizedTest
//    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers
//    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
//        assertTrue(Numbers.isOdd(number));
//    }
    static Stream<Arguments> arguments = Stream.of(
            Arguments.of(1D, "good")
    );

    @ParameterizedTest
    @ArgumentsSource(RankCommentArgumentProvider.class)
    void findByJoin(double number,String comment) {
        //given
        MovieComment movieComment1 = new MovieComment("good movie");
        MovieComment movieComment2 = new MovieComment("good movie ,I enjoyed");

        Movie movie = new Movie("Terminator", "Action", 6D, 1989D, 1999);
        movie.setMovieComments(Set.of(movieComment1, movieComment2));
        movieComment1.setMovie(movie);
        movieComment2.setMovie(movie);

        MovieComment movieComment3 = new MovieComment("awesome movie");
        MovieComment movieComment4 = new MovieComment("wonderful");

        Movie movie2 = new Movie("Avatar", "Action", 7D, 19489D, 1991);
        movie2.setMovieComments(Set.of(movieComment3, movieComment4));
        movieComment3.setMovie(movie2);
        movieComment4.setMovie(movie2);


        Movie p1 = testEntityManager.persistFlushFind(movie);
        Movie p2 = testEntityManager.persistFlushFind(movie2);
        List<Movie> persisted = new ArrayList<>();
        Collections.addAll(persisted, p1, p2);

        //when
        List<Movie> result = movieRepository.findByJoin(number, comment,"","");
        //then
        then(persisted.size()).isEqualTo(result.size());
        then(result).isNotEmpty();
    }
}
package com.example.specification.repositories.custom;

import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComments;

import java.util.List;
public interface MovieCustomRepository {
    List<Movie> findAllByTitleLike(String title);

    List<MovieComments> findByJoin(Double rating, String comment);
}

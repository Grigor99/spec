package com.example.specification.repositories.custom;


import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComment;

import java.util.List;

public interface MovieCustomRepository {
    List<Movie> findAllByTitleLike(String title);

    List<MovieComment> findByJoin(Double rating, String comment);
}

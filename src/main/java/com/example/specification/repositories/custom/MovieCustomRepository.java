package com.example.specification.repositories.custom;


import com.example.specification.domains.Movie;

import java.util.List;

public interface MovieCustomRepository {
    List<Movie> findAllByTitleLike(String title);

    List<Movie> findByJoin(Double rating, String comment);
}

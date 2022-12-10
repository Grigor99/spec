package com.example.specification.repositories.custom;


import com.example.specification.domains.Movie;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

public interface MovieCustomRepository {
    List<Movie> findAllByTitleLike(String title);

    JPAQuery<Tuple> findByJoin(Double rating, String comment);
}

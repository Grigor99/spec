package com.example.specification.servcie.abst;

import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComment;
import com.example.specification.repositories.specs.SearchCriteria;

import java.util.List;

public interface MovieService {
    List<Movie> search(List<SearchCriteria> searchCriteriaList);

    List<Movie> findAllByTitleLike(String title);

    List<?> findByJoin(Double rate, String comment);



    List<Movie> getByTitle(String name);

    List<Movie> findByRatingBetween(Double min, Double max);
}

package com.example.specification.servcie.abst;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.specs.SearchCriteria;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> search(List<SearchCriteria> searchCriteriaList);

    List<Movie> findAllByTitleLike(String title);

    List<?> findByJoin(Double rate, String comment);
    Movie findById(Long id);



    List<Movie> getByTitle(String name);

    List<Movie> findByRatingBetween(Double min, Double max);
}

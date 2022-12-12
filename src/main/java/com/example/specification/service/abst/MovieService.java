package com.example.specification.service.abst;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.enumaration.SearchCriteria;

import java.util.List;

public interface MovieService {
    List<Movie> search(List<SearchCriteria> searchCriteriaList);

    List<Movie> findAllByTitleLike(String title);

    List<Movie> findByJoin(Double rate, String comment);
    Movie findById(Long id);



    List<Movie> getByTitle(String name);

    List<Movie> findByRatingBetween(Double min, Double max);
}

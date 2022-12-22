package com.example.specification.service.abst;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.enumaration.SearchCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MovieService {
    List<Movie> search(List<SearchCriteria> searchCriteriaList);

    @Transactional
    Movie findByIdWithReadLock(Long id);

    List<Movie> findAllByTitleLike(String title);

    List<Movie> findByJoin(Double rate, String comment1, String comment2, String comment3);

    Movie findById(Long id);

    List<Movie> getByTitle(String name);

    List<Movie> findByRatingBetween(Double min, Double max);
}
package com.example.specification.servcie;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.specs.SearchCriteria;
import com.querydsl.core.Tuple;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface MovieService {
    List<Movie> search(List<SearchCriteria> searchCriteriaList);


    List<Movie> findAllByTitleLike(String title);

    List<Movie> getByRatingAndTitleSearch();

    List<Movie> getTitleLikeAvatar();

}

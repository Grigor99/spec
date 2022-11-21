package com.example.specification.servcie;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.specs.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface MovieService {
    List<Movie> search(List<SearchCriteria> searchCriteriaList);

    List<Movie> getByRatingAndTitleSearch();


    List<Movie> getTitleLikeAvatar();

}

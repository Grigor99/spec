package com.example.specification.servcie;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.specs.SearchCriteria;

import java.util.List;

public interface MovieService {
    List<Movie> search(List<SearchCriteria> searchCriteriaList);

    List<Movie> getTitleLikeAvatar();

    List<Movie> getByRatingCriteria();
}

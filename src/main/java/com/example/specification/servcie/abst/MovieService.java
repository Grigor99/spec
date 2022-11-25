package com.example.specification.servcie.abst;

import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComments;
import com.example.specification.repositories.specs.SearchCriteria;
import com.querydsl.core.Tuple;


import java.util.List;

public interface MovieService {
    List<Movie> search(List<SearchCriteria> searchCriteriaList);


    List<Movie> findAllByTitleLike(String title);

    List<MovieComments> findByJoin(Double rate, String comment);

//    List<Movie> getByRatingAndTitleSearch();
//
//    List<Movie> getTitleLikeAvatar();

}

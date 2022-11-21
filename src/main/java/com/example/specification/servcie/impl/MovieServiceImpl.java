package com.example.specification.servcie.impl;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.MovieRepository;
import com.example.specification.repositories.specs.MovieSpecification;
import com.example.specification.repositories.specs.SearchCriteria;
import com.example.specification.repositories.specs.SearchOperation;
import com.example.specification.servcie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;


    @Override
    public List<Movie> search(List<SearchCriteria> searchCriteriaList) {
        MovieSpecification specification = new MovieSpecification();
        searchCriteriaList.stream().map(criteria -> new SearchCriteria(criteria.getKey(), criteria.getValue(), criteria.getOperation())).forEach(specification::add);
        return movieRepository.findAll(specification);
    }







    public Specification<Movie> getByRating() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("rating"), 5.0);
    }

    @Override
    public List<Movie> getTitleLikeAvatar() {
        MovieSpecification specification = new MovieSpecification();
        specification.add(new SearchCriteria("title", "Avatar", SearchOperation.MATCH));
        return movieRepository.findAll(specification);
    }
    @Override
    public List<Movie> getByRatingCriteria() {
        Specification<Movie> specification = getByRating();
        return movieRepository.findAll(specification);
    }

}

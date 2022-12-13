package com.example.specification.service.impl;

import com.example.specification.domains.Movie;
import com.example.specification.exceptions.NotFoundException;
import com.example.specification.repositories.MovieRepository;
import com.example.specification.repositories.specs.MovieSpecification;
import com.example.specification.repositories.enumaration.SearchCriteria;
import com.example.specification.service.abst.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> search(List<SearchCriteria> searchCriteriaList) {
        MovieSpecification specification = new MovieSpecification();
        searchCriteriaList.stream().map(criteria -> new SearchCriteria(criteria.key(), criteria.value(), criteria.searchOperation())).forEach(specification::add);
        return movieRepository.findAll(specification);
    }

    @Override
    public List<Movie> findAllByTitleLike(String title) {
        return movieRepository.findAllByTitleLike(title);
    }

    @Override
    public List<Movie> findByJoin(Double rate, String comment1, String comment2, String comment3) {
        return movieRepository.findByJoin(rate, comment1, comment2, comment3);
    }

    @Override
    @Cacheable("moviecache")
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new NotFoundException("movie.not.found"));
    }

    @Override
    public List<Movie> getByTitle(String name) {
        return movieRepository.getByTitle(name);
    }

    @Override
    public List<Movie> findByRatingBetween(Double min, Double max) {
        return movieRepository.findByRatingBetween(min, max);
    }
}
package com.example.specification.servcie.impl;

import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComment;
import com.example.specification.repositories.MovieRepository;
import com.example.specification.repositories.specs.MovieSpecification;
import com.example.specification.repositories.specs.SearchCriteria;
import com.example.specification.servcie.abst.MovieService;
import lombok.RequiredArgsConstructor;
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
    public List<?> findByJoin(Double rate, String comment) {
        return movieRepository.findByJoin(rate, comment);
    }

//
//
//    @Override
//    public List<Movie> getByRatingAndTitleSearch() {
//        return movieRepository.findAll(getByRatingAndTitle());
//    }
//
//
//    public Specification<Movie> getByRatingAndTitle() {
//        return (root, query, criteriaBuilder) -> {
//            query.groupBy(root.get("title"), root.get("id")).orderBy(criteriaBuilder.desc(root.get("rating")));
//            List<Predicate> predicates = new ArrayList<>();
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), 5.0));
//            predicates.add(criteriaBuilder.like(root.get("title"), "%" + "Ava" + "%"));
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//
//        };
//    }
//
//    @Override
//    public List<Movie> getTitleLikeAvatar() {
//        MovieSpecification specification = new MovieSpecification();
//        specification.add(new SearchCriteria("title", "Avatar", SearchOperation.MATCH));
//        return movieRepository.findAll(specification);
//    }

}

package com.example.specification.repositories.specs;

import com.example.specification.domains.Movie;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MovieSpecification implements Specification<Movie> {
    private final List<SearchCriteria> list;

    public MovieSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate(@NonNull Root<Movie> root, @NonNull CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        list.forEach(criteria -> {
            switch (criteria.searchOperation()) {
                case GREATER_THAN ->
                        predicates.add(builder.greaterThan(root.get(criteria.key()), criteria.value().toString()));
                case LESS_THAN ->
                        predicates.add(builder.lessThan(root.get(criteria.key()), criteria.value().toString()));
                case GREATER_THAN_EQUAL ->
                        predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.key()), criteria.value().toString()));
                case LESS_THAN_EQUAL ->
                        predicates.add(builder.lessThanOrEqualTo(root.get(criteria.key()), criteria.value().toString()));
                case NOT_EQUAL -> predicates.add(builder.notEqual(root.get(criteria.key()), criteria.value()));
                case EQUAL -> predicates.add(builder.equal(root.get(criteria.key()), criteria.value()));
                case MATCH ->
                        predicates.add(builder.like(builder.lower(root.get(criteria.key())), "%" + criteria.value().toString().toLowerCase() + "%"));
                case MATCH_END ->
                        predicates.add(builder.like(builder.lower(root.get(criteria.key())), criteria.value().toString().toLowerCase() + "%"));
                case MATCH_START ->
                        predicates.add(builder.like(builder.lower(root.get(criteria.key())), "%" + criteria.value().toString().toLowerCase()));
                case IN -> predicates.add(builder.in(root.get(criteria.key())).value(criteria.value()));
                case NOT_IN -> predicates.add(builder.not(root.get(criteria.key())).in(criteria.value()));
                default -> throw new RuntimeException("not supported operation");
            }
        });
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}

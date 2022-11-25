package com.example.specification.repositories;

import com.example.specification.domains.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie,Long>,MovieCustomRepository, JpaSpecificationExecutor<Movie> {

    Movie getByTitle(String name);

    List<Movie> findByTitleIgnoreCase(String title);
}

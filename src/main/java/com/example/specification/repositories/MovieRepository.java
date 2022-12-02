package com.example.specification.repositories;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.custom.MovieCustomRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, MovieCustomRepository, JpaSpecificationExecutor<Movie> {

    List<Movie> getByTitle(String name);

    List<Movie> findByRatingBetween(Double min,Double max);
}

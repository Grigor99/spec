package com.example.specification.repositories;

import com.example.specification.domains.Movie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
@Repository
public interface MovieRepository extends CrudRepository<Movie,Long>, JpaSpecificationExecutor<Movie> {

}

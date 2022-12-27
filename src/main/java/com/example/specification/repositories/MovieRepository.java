package com.example.specification.repositories;

import com.example.specification.repositories.custom.MovieCustomRepository;
import com.example.specification.domains.Movie;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, MovieCustomRepository, JpaSpecificationExecutor<Movie> {
    List<Movie> getByTitle(String name);

    @Override
    Optional<Movie> findById(Long id);

    //read if other transaction try to update or insert will throw exception
    @Transactional()
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT m FROM Movie m where m.id = :id")
    Movie findByIdWithReadLock(@Param("id") Long id);

    List<Movie> findByRatingBetween(Double min, Double max);
}
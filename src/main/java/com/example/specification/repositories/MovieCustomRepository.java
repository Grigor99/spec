package com.example.specification.repositories;

import com.example.specification.domains.Movie;
import com.querydsl.core.Tuple;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
public interface MovieCustomRepository {
    List<Movie> findAllByTitleLike(String title);
}

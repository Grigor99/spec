package com.example.specification.repositories;


import com.example.specification.domains.Movie;
import com.example.specification.domains.QMovie;
import com.example.specification.domains.QMovieComments;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    @PersistenceContext
    private final EntityManager em;


    @Override
    public List<Movie> findAllByTitleLike(String title) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QMovie qMovie = QMovie.movie;

        return queryFactory.selectFrom(qMovie)
                .where(qMovie.title.like("%"+title+"%"))
                .fetch();
    }
}

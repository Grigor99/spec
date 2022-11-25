package com.example.specification.repositories.custom;


import com.example.specification.domains.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
                .where(qMovie.title.like("%" + title + "%"))
                .fetch();
    }


    @Override
    @Transactional(readOnly = true)
    public List<MovieComments> findByJoin(Double rating, String comment) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QMovie qMovie = QMovie.movie;
        QMovieComments qMovieComments = QMovieComments.movieComments;

        return queryFactory.selectFrom(qMovie)
                .select(qMovieComments)
                .leftJoin(qMovie.movieComments, qMovieComments)
                .on(qMovie.id.eq(qMovieComments.movie.id))
                .where(qMovie.rating.gt(rating).and(qMovieComments.comment.like("%" + comment + "%")))
                .groupBy(qMovie.title, qMovie.id, qMovieComments.id)
                .orderBy(qMovie.rating.asc())
                .fetch();
    }
}

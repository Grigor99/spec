package com.example.specification.repositories.custom;


import com.example.specification.domains.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.specification.domains.QMovie.movie;
import static com.example.specification.domains.QMovieComment.movieComment;


@RequiredArgsConstructor
public class MovieCustomRepositoryImpl implements MovieCustomRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Movie> findAllByTitleLike(String title) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMovie qMovie = movie;
        return queryFactory.selectFrom(qMovie).where(qMovie.title.like("%" + title + "%")).fetch();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findByJoin(Double rating, String comment) {
        QMovie qMovie = movie;
        QMovieComment qMovieComments = movieComment;
        return new JPAQueryFactory(this.em).
                selectFrom(qMovie).
                leftJoin(qMovie.movieComments, qMovieComments).
                on(qMovie.id.eq(qMovieComments.movie.id)).
                where(qMovie.rating.gt(rating).and(qMovieComments.comment.likeIgnoreCase("%" + comment + "%")))
                .distinct().fetch();
    }
}

package com.example.specification.repositories.custom;


import com.example.specification.domains.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
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
    public JPAQuery<Tuple> findByJoin(Double rating, String comment) {


//        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMovie qMovie = movie;
        QMovieComment qMovieComments = movieComment;
        JPAQuery<Tuple> query = new JPAQuery<>(em);

        return query.select(qMovie,qMovieComments)
                .from(qMovie)
                .join(qMovieComments)
                .on(qMovie.id.eq(qMovieComments.movie.id));
//        return Collections.unmodifiableList(
//                queryFactory
//                        .selectFrom(qMovie)
//                        .join(qMovie.movieComments, qMovieComments)
//                        .on(qMovie.id.eq(qMovieComments.movie.id))
//                        .where(qMovie.rating.gt(rating).and(qMovieComments.comment.likeIgnoreCase("%" + comment + "%")))
//                        .groupBy(qMovie.title, qMovie.id, qMovieComments.id)
//                        .orderBy(qMovie.rating.asc())
//                        .stream().toList());
    }
}

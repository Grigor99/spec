package com.example.specification.repositories;

import com.example.specification.domains.MovieComments;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;




@Repository
public interface MovieCommentsRepository extends JpaRepository<MovieComments,Long> , JpaSpecificationExecutor<MovieComments> {

    MovieComments getByComment(String comment);

    MovieComments findByCommentAndMovie_TitleAllIgnoringCase(@NonNull String comment, @NonNull String movieTitle);
}

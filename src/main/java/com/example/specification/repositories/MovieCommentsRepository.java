package com.example.specification.repositories;

import com.example.specification.domains.MovieComment;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCommentsRepository extends JpaRepository<MovieComment, Long>, JpaSpecificationExecutor<MovieComment> {
    MovieComment getByComment(String comment);

    MovieComment findByCommentAndMovie_TitleAllIgnoringCase(@NonNull String comment, @NonNull String movieTitle);
}
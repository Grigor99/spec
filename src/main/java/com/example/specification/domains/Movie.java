package com.example.specification.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
@Entity(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private Double rating;
    private Double watchTime;
    private Integer releaseYear;
    @ToString.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "movie", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<MovieComment> movieComments = new LinkedHashSet<>();

    public void addMovieComment(MovieComment comment) {
        movieComments.add(comment);
        comment.setMovie(this);
    }

    public void removeComment(MovieComment comment) {
        movieComments.remove(comment);
        comment.setMovie(null);
    }
}

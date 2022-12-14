package com.example.specification.domains;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
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
@Builder(toBuilder = true)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private Double rating;
    @Column(name = "watch_time")
    private Double watchTime;
    @Column(name = "release_year")
    private Integer releaseYear;
    @JsonManagedReference
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @ToString.Exclude
    private Set<MovieComment> movieComments = new LinkedHashSet<>();

    public Movie(Long id, String title, String genre, Double rating, Double watchTime, Integer releaseYear) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.watchTime = watchTime;
        this.releaseYear = releaseYear;
    }

    public Movie(String title, String genre, Double rating, Double watchTime, Integer releaseYear) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.watchTime = watchTime;
        this.releaseYear = releaseYear;
    }

    public void addMovieComment(MovieComment comment) {
        movieComments.add(comment);
        comment.setMovie(this);
    }

    public void removeComment(MovieComment comment) {
        movieComments.remove(comment);
        comment.setMovie(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(genre, movie.genre) && Objects.equals(rating, movie.rating) && Objects.equals(watchTime, movie.watchTime) && Objects.equals(releaseYear, movie.releaseYear) && Objects.equals(movieComments, movie.movieComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, rating, watchTime, releaseYear, movieComments);
    }
}
package com.example.specification.domains;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private double rating;
    private double watchTime;
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "cinema_id",nullable = false)
    private Cinema cinema;
}

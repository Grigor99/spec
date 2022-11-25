package com.example.specification.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String title;
    private String genre;
    private double rating;
    private double watchTime;
    private int releaseYear;


    private String comment;

    private Integer rank;
}

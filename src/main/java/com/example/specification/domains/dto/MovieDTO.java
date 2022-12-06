package com.example.specification.domains.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO extends AbstractDTO{
    private Long id;
    private String title;
    private String genre;
    private Double rating;
    private Double watchTime;
    private Integer releaseYear;
}

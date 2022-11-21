package com.example.specification.controller;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.specs.SearchCriteria;
import com.example.specification.servcie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> searchByGenre(@RequestBody List<SearchCriteria> searchCriteriaList) {
        return ResponseEntity.ok(movieService.search(searchCriteriaList));
    }

}

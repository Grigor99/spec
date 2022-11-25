package com.example.specification.controller;

import com.example.specification.domains.Movie;
import com.example.specification.repositories.specs.SearchCriteria;
import com.example.specification.servcie.abst.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> search(@RequestBody List<SearchCriteria> searchCriteriaList) {
        return ResponseEntity.ok(movieService.search(searchCriteriaList));
    }


    @GetMapping("/like")
    public ResponseEntity<List<Movie>> getByTitleLike(@RequestParam(name = "title") String title) {
        return ResponseEntity.ok(movieService.findAllByTitleLike(title));
    }


}

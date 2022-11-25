package com.example.specification.controller;

import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComments;
import com.example.specification.repositories.specs.SearchCriteria;
import com.example.specification.servcie.abst.MovieService;
import com.querydsl.core.Tuple;
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

    @GetMapping("/join")
    public ResponseEntity<List<MovieComments>> getByJoin(@RequestParam Double rating, @RequestParam String comment) {
        return ResponseEntity.ok(movieService.findByJoin(rating, comment));
    }


    @GetMapping("/like")
    public ResponseEntity<List<Movie>> getByTitleLike(@RequestParam(name = "title") String title) {
        return ResponseEntity.ok(movieService.findAllByTitleLike(title));
    }


}

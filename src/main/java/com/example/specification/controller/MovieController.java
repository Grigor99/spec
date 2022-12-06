package com.example.specification.controller;

import com.example.specification.domains.Movie;
import com.example.specification.domains.MovieComment;
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

    @GetMapping("/{title}")
    public ResponseEntity<List<Movie>> getByTitle(@PathVariable(name = "title") String title) {
        return ResponseEntity.ok(movieService.getByTitle(title));
    }

    @GetMapping("/byRating/{min}/{max}")
    public ResponseEntity<List<Movie>> getByTitle(@PathVariable(name = "min") Double min,
                                                  @PathVariable(name = "max") Double max) {
        return ResponseEntity.ok(movieService.findByRatingBetween(min, max));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Movie> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }
//    @GetMapping("/{rating}/{comment}")
//    public ResponseEntity<List<?>> getByJoin(@PathVariable(name = "rating") Double rating,
//                                             @PathVariable(name = "comment") String comment) {
//        return ResponseEntity.ok(movieService.findByJoin(rating, comment));
//    }

//    @GetMapping("")
//    public ResponseEntity<List<Movie>> getByTitleLike(@RequestParam(name = "title") String title) {
//        return ResponseEntity.ok(movieService.findAllByTitleLike(title));
//    }
}

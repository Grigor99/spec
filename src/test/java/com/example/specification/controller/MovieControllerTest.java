package com.example.specification.controller;

import com.example.specification.domains.Movie;
import com.example.specification.exceptions.NotFoundException;
import com.example.specification.service.abst.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void getById() throws Exception {

        given(movieService.findById(anyLong())).
                willReturn(new Movie(1L, "name", "action", 5D, 1999D, 354));

        mockMvc.perform(get("/movies/id/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value("name"))
                .andExpect(jsonPath("genre").value("action"))
                .andExpect(jsonPath("rating").value(5D))
                .andExpect(jsonPath("releaseYear").value(354))
                .andExpect(jsonPath("watchTime").value(1999D));

    }

    @Test
    void getById_withNotFoundException() throws Exception {

        given(movieService.findById(anyLong())).
                willThrow(NotFoundException.class);

        mockMvc.perform(get("/movies/id/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    void getByJoin() throws Exception {
        Movie movie1 = new Movie(1L, "Avatar", "Action", 5D, 1999D, 1991);
        Movie movie2 = new Movie(2L, "Transformers", "Action", 6D, 13339D, 1998);
        given(movieService.findByJoin(anyDouble(), anyString(), anyString(), anyString())).
                willReturn(Arrays.asList(movie1, movie2));

        mockMvc.perform(get("/movies/byJOIN?rating=4&comment1=good&comment2=awesome&comment3=wonderful"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].title", equalTo("Avatar")))
                .andExpect(jsonPath("$[0].genre", equalTo("Action")))
                .andExpect(jsonPath("$[0].rating", equalTo(5D)))
                .andExpect(jsonPath("$[0].releaseYear", equalTo(1991)))
                .andExpect(jsonPath("$[0].watchTime", equalTo(1999D)))
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].title", equalTo("Transformers")))
                .andExpect(jsonPath("$[1].genre", equalTo("Action")))
                .andExpect(jsonPath("$[1].rating", equalTo(6D)))
                .andExpect(jsonPath("$[1].releaseYear", equalTo(1998)))
                .andExpect(jsonPath("$[1].watchTime", equalTo(13339D)));
    }
}
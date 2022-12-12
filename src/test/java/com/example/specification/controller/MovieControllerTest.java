package com.example.specification.controller;

import com.example.specification.domains.Movie;
import com.example.specification.exceptions.NotFoundException;
import com.example.specification.service.abst.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
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
}
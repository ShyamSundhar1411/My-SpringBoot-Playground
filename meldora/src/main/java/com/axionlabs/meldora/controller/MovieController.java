package com.axionlabs.meldora.controller;

import com.axionlabs.meldora.dto.movie.MovieDto;
import com.axionlabs.meldora.service.impl.MovieServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
public class MovieController{
    @Autowired
    private MovieServiceImpl IMovieService;

    @QueryMapping
    public List<MovieDto> fetchMovies(@Argument String query, @Argument String language, @Argument int page){
        return IMovieService.getMovies(query,language,page);

    }

    @QueryMapping
    public MovieDto fetchMovieById(@Argument Integer movieId){
        return IMovieService.getMovieById(movieId);
    }
}

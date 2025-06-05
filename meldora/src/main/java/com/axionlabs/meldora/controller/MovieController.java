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
    private MovieServiceImpl ImovieService;

    @QueryMapping
    public List<MovieDto> movies(@Argument String query, @Argument String language, @Argument int page){
        System.out.println("Hey");
        return ImovieService.getMovie(query,language,page);

    }
}

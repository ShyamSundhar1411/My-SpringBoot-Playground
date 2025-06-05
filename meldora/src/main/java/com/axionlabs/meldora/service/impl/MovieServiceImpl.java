package com.axionlabs.meldora.service.impl;

import com.axionlabs.meldora.dto.movie.MovieDto;
import com.axionlabs.meldora.dto.movie.response.ListMovieResponseDto;
import com.axionlabs.meldora.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service

public class MovieServiceImpl implements MovieService {
    @Autowired
    @Qualifier("tmdbWebClient")
    private WebClient tmdbWebClient;


    @Override
    public List<MovieDto> getMovie(String query, String language, Integer page) {

        String uri = String.format("/search/movie?query=%s&language=%s&page=%d", query, language, page);


        ListMovieResponseDto response = tmdbWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ListMovieResponseDto.class)
                .block();

        return response != null ? response.getResults() : Collections.emptyList();
    }

}

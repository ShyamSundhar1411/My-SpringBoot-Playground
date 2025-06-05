package com.axionlabs.meldora.service.impl;

import com.axionlabs.meldora.dto.movie.GenreDto;
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

    @Autowired
    private GenreServiceImpl IGenreService;


    @Override
    public List<MovieDto> getMovie(String query, String language, Integer page) {

        String uri = String.format("/search/movie?query=%s&language=%s&page=%d", query, language, page);
        ListMovieResponseDto response = tmdbWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ListMovieResponseDto.class)
                .block();
        if (response == null || response.getResults() == null) {
            return Collections.emptyList();
        }
        return response.getResults().stream()
                .peek(movieDto -> {
                    List<GenreDto> genres = IGenreService.mapGenreIdsToGenres(movieDto.getGenreIds());
                    movieDto.setGenres(genres);
                })
                .toList();
    }

    @Override
    public MovieDto getMovieById(Integer movieId) {
        String uri = String.format("/movie/%s",movieId.toString());
        MovieDto response = tmdbWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(MovieDto.class)
                .block();
        return response != null ? response : new MovieDto();

    }

}

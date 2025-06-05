package com.axionlabs.meldora.service.impl;

import com.axionlabs.meldora.dto.movie.GenreDto;
import com.axionlabs.meldora.dto.movie.response.ListGenreResponseDto;
import com.axionlabs.meldora.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service

public class GenreServiceImpl implements GenreService {
    @Autowired
    @Qualifier("tmdbWebClient")
    private WebClient tmdbWebClient;
    @Override
    @Cacheable("genres")
    public List<GenreDto> getAllGenres() {
        String uri = "/genre/movie/list";
        ListGenreResponseDto response = tmdbWebClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ListGenreResponseDto.class)
                .block();
        return response!= null ? response.getGenres():List.of();
    }
    @Override
    public List<GenreDto> mapGenreIdsToGenres(List<Integer> genreIds) {
        List<GenreDto> allGenres = getAllGenres();
        Map<Integer,GenreDto> genreMap = allGenres.stream()
                .collect(Collectors.toMap(GenreDto::getId, Function.identity()));
        return genreIds.stream()
                .map(
                        genreMap::get
                ).filter(
                        Objects::nonNull
                ).collect(
                        Collectors.toList()
                );
    }

}


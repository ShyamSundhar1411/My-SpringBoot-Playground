package com.axionlabs.meldora.service;

import com.axionlabs.meldora.dto.movie.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAllGenres();
    List<GenreDto> mapGenreIdsToGenres(List<Integer> genreIds);
}

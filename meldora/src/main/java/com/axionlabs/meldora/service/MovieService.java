package com.axionlabs.meldora.service;


import com.axionlabs.meldora.dto.movie.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovie(String query, String language, Integer page);
}

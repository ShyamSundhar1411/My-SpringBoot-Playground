package com.axionlabs.meldora.dto.movie.response;

import com.axionlabs.meldora.dto.movie.MovieDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter @Setter
public class ListMovieResponseDto {
    private int page;
    private List<MovieDto> results;
    private int totalPages;
    private int totalResults;

}

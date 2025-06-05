package com.axionlabs.meldora.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter @Setter @AllArgsConstructor
public class MovieDto {
    private Long id;
    private String backdropPath;
    private List<GenreDto> genres;
    private String originalLanguage;
    private String overview;
    private Float popularity;
    private String posterPath;
    private String releaseDate;
    private String title;
    private Boolean video;
    private Float voteAverage;
    private Integer voteCount;

}

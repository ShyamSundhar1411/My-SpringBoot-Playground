package com.axionlabs.meldora.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MovieDto {
    private Long id;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    private Boolean adult;
    @JsonProperty("genre_ids")
    private List<Integer> genreIds;
    private List<GenreDto> genres;
    @JsonProperty("original_language")
    private String originalLanguage;
    private String overview;
    private Float popularity;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("release_date")
    private String releaseDate;
    private String title;
    private Boolean video;
    @JsonProperty("vote_average")
    private Float voteAverage;
    @JsonProperty("vote_count")
    private Integer voteCount;

}

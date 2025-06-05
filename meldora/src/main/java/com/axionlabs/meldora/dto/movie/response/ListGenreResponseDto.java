package com.axionlabs.meldora.dto.movie.response;

import com.axionlabs.meldora.dto.movie.GenreDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter @Setter
public class ListGenreResponseDto {
    List<GenreDto> genres;
}

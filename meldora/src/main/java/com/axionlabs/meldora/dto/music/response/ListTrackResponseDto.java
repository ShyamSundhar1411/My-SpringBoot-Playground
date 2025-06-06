package com.axionlabs.meldora.dto.music.response;

import com.axionlabs.meldora.dto.music.TrackDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class ListTrackResponseDto {
    TrackDto tracks;
}

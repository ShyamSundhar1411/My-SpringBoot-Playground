package com.axionlabs.meldora.dto.music.response;

import com.axionlabs.meldora.dto.music.ItemDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class TrackResponseDto {
    private ItemDto track;
}

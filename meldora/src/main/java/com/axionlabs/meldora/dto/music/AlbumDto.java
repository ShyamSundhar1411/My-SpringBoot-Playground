// AlbumDto.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.axionlabs.meldora.dto.music;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import java.time.LocalDate;

@Data
@Getter @Setter
public class AlbumDto {
    private List<ImageDto> images;
    @JsonProperty("available_markets")
    private List<String> availableMarkets;
    private String releaseDatePrecision;
    private String type;
    private String uri;
    private long totalTracks;
    private boolean isPlayable;
    private List<ArtistDto> artists;
    private LocalDate releaseDate;
    private String name;
    private String albumType;
    private String href;
    private String id;
    private ExternalUrlsDto externalUrls;


}



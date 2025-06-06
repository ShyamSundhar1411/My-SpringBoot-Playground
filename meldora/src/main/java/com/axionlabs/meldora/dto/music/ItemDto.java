package com.axionlabs.meldora.dto.music;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto {
    private long discNumber;
    private AlbumDto album;
    private List<String> availableMarkets;
    private String type;
    private ExternalIdsDto externalIds;
    private String uri;
    private long durationMs;
    private boolean explicit;
    private boolean isPlayable;
    private List<ArtistDto> artists;
    private long popularity;
    private String name;
    private long trackNumber;
    private String href;
    private String id;
    @JsonProperty("is_local")
    private boolean isLocal;
    private ExternalUrlsDto externalUrls;
}
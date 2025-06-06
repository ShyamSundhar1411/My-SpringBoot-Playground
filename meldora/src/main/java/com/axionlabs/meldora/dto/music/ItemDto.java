package com.axionlabs.meldora.dto.music;

import lombok.Data;

import java.util.List;

@Data
class ItemDto {
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
    private boolean isLocal;
    private ExternalUrlsDto externalUrls;
}
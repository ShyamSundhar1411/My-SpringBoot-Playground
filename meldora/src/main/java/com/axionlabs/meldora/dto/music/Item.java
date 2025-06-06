package com.axionlabs.meldora.dto.music;

import lombok.Data;

import java.util.List;

@Data
class Item {
    private long discNumber;
    private Album album;
    private List<String> availableMarkets;
    private ItemType type;
    private ExternalIds externalids;
    private String uri;
    private long durationms;
    private boolean explicit;
    private boolean isPlayable;
    private List<Artist> artists;
    private long popularity;
    private String name;
    private long trackNumber;
    private String href;
    private String id;
    private boolean isLocal;
    private ExternalUrls externalUrls;
}
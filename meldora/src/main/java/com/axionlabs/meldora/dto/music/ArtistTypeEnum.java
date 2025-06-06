package com.axionlabs.meldora.dto.music;

import java.io.IOException;

public enum ArtistTypeEnum {
    ARTIST;

    public String toValue() {
        switch (this) {
            case ARTIST: return "artist";
        }
        return null;
    }

    public static ArtistTypeEnum forValue(String value) throws IOException {
        if (value.equals("artist")) return ARTIST;
        throw new IOException("Cannot deserialize ArtistTypeEnum");
    }
}
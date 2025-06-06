package com.axionlabs.meldora.dto.music;

import java.io.IOException;

public enum ItemType {
    TRACK;

    public String toValue() {
        if (this == ItemType.TRACK) {
            return "track";
        }
        return null;
    }

    public static ItemType forValue(String value) throws IOException {
        if (value.equals("track")) return TRACK;
        throw new IOException("Cannot deserialize ItemType");
    }
}
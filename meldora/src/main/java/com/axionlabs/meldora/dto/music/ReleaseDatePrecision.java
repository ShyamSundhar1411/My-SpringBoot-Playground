package com.axionlabs.meldora.dto.music;

import java.io.IOException;

public enum ReleaseDatePrecision {
    DAY;

    public String toValue() {
        switch (this) {
            case DAY: return "day";
        }
        return null;
    }

    public static ReleaseDatePrecision forValue(String value) throws IOException {
        if (value.equals("day")) return DAY;
        throw new IOException("Cannot deserialize ReleaseDatePrecision");
    }
}

package com.axionlabs.meldora.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spotify.config")
public class SpotifyConfigProperties {
    private String clientId;
    private String clientSecret;
}

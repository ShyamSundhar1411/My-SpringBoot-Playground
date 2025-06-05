package com.axionlabs.meldora.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spotify.config")
@Getter @Setter
public class SpotifyConfigProperties {
    private String clientId;
    private String clientSecret;
    private String baseUrl;
}

package com.axionlabs.meldora.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tmdb.config")
@Getter @Setter
public class TMDBConfigProperties {

    private String readAccessToken;
    private String apiKey;
    private String baseUrl;

}

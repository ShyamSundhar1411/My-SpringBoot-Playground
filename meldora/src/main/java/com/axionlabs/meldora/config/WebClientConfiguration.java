package com.axionlabs.meldora.config;

import com.axionlabs.meldora.config.properties.SpotifyConfigProperties;
import com.axionlabs.meldora.config.properties.TMDBConfigProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
    private TMDBConfigProperties tmdbConfigProperties;
    private SpotifyConfigProperties spotifyConfigProperties;

    @Bean
    @Qualifier("tmdbWebClient")
    public WebClient tmdaWebClient(){
        return WebClient.builder()
                .baseUrl(tmdbConfigProperties.getBaseUrl())
                .defaultHeader("Authorization","Bearer "+tmdbConfigProperties.getReadAccessToken())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    @Qualifier("spotifyWebClient")
    public WebClient spotifyWebClient(){
        return WebClient.builder()
                .baseUrl(spotifyConfigProperties.getBaseUrl())
                .defaultHeader("Authorization", "Bearer "+spotifyConfigProperties.getClientId())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }



}

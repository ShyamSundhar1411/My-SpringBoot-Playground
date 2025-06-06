package com.axionlabs.meldora.config;

import com.axionlabs.meldora.config.properties.SpotifyConfigProperties;
import com.axionlabs.meldora.config.properties.TMDBConfigProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class WebClientConfiguration {
    private final TMDBConfigProperties tmdbConfigProperties;
    private final SpotifyConfigProperties spotifyConfigProperties;
    @Autowired
    public WebClientConfiguration(TMDBConfigProperties tmdbConfigProperties,
                                  SpotifyConfigProperties spotifyConfigProperties) {
        this.tmdbConfigProperties = tmdbConfigProperties;
        this.spotifyConfigProperties = spotifyConfigProperties;
    }

    @Bean
    @Qualifier("tmdbWebClient")
    public WebClient tmdaWebClient(){
        return WebClient.builder()
                .baseUrl(tmdbConfigProperties.getBaseUrl())
                .defaultHeader("Authorization","Bearer "+tmdbConfigProperties.getReadAccessKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    @Qualifier("spotifyWebClient")
    public WebClient spotifyWebClient(){
        String credentials = spotifyConfigProperties.getClientId()+":"+spotifyConfigProperties.getClientSecret();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        WebClient tokenClient = WebClient.builder()
                .baseUrl(spotifyConfigProperties.getAuthUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
        String accessToken = tokenClient
                .post()
                .body(BodyInserters.fromFormData("grant_type","client_credentials"))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .map(TokenResponse::getAccessToken)
                .block();
        return WebClient.builder()
                .baseUrl(spotifyConfigProperties.getBaseUrl())
                .defaultHeader("Authorization", "Bearer "+accessToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    @Setter @Getter
    private static class TokenResponse {
        @JsonProperty("access_token")
        private String accessToken;
    }


}

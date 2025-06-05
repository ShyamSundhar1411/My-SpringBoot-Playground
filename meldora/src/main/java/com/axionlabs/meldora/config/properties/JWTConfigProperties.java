package com.axionlabs.meldora.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.token")
public class JWTConfigProperties {
    private String accessSecretKey;
    private String refreshSecretKey;
    private String accessSecretKeyExpiration;
    private String refreshSecretKeyExpiration;
}

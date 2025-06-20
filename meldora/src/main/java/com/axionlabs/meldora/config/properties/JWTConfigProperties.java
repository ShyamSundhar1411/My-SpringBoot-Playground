package com.axionlabs.meldora.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.token")
@Getter @Setter
public class JWTConfigProperties {
    private String accessSecretKey;
    private String refreshSecretKey;
    private Long accessSecretKeyExpiration;
    private Long refreshSecretKeyExpiration;
}

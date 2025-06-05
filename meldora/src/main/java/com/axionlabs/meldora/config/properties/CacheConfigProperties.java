package com.axionlabs.meldora.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.cache.config")
@Getter @Setter
public class CacheConfigProperties {
    private Long cacheExpiration;
}

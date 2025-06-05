package com.axionlabs.meldora.config;

import com.axionlabs.meldora.config.properties.CacheConfigProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CaffeineCacheConfiguration {
    @Autowired
    private CacheConfigProperties cacheConfigProperties;
    @Bean
    public Caffeine<Object,Object> caffeineConfig(){
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterWrite(cacheConfigProperties.getCacheExpiration(),TimeUnit.MINUTES);
    }
    @Bean
    public CacheManager cacheManager(Caffeine<Object,Object> caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}

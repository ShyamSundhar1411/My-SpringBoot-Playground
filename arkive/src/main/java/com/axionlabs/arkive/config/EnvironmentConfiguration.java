package com.axionlabs.arkive.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentConfiguration implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /**
     * Initialize the given application context.
     *
     * @param applicationContext the application context to bootstrap
     */
    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        Map<String,Object> envMap = new HashMap<>();
        dotenv.entries().forEach(entry -> envMap.put(entry.getKey(),entry.getValue()));
        applicationContext.getEnvironment().getPropertySources().addFirst(new MapPropertySource("dotenv",envMap));
    }
}

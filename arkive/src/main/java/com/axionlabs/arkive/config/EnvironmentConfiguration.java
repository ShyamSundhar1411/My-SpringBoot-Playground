package com.axionlabs.arkive.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class EnvironmentConfiguration implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /**
     * Initialize the given application context.
     *
     * @param applicationContext the application context to bootstrap
     */
    @Override
    public void initialize(@NotNull ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    }
}

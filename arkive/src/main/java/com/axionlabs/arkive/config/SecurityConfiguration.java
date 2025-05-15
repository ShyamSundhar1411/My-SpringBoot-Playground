package com.axionlabs.arkive.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JWTAuthenticatIonFilter jwtAuthenticatIonFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                        "/api/v1/auth/**",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/swagger-ui.html",
                                        "/webjars/**",
                                        "/api/v1/token/**",
                                "/actuator/**"

                                ).permitAll()
                                .anyRequest().authenticated()
                        ).sessionManagement(
                                session -> session.sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                ).authenticationProvider(
                        authenticationProvider
                ).addFilterBefore(
                        jwtAuthenticatIonFilter, UsernamePasswordAuthenticationFilter.class
                );
                return httpSecurity.build();
    }

}

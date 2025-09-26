package com.xoslu.tech.bookmanagment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final static String ENDPOINTS_PUBLIC = "/api/v1/books/public";
    private final static String ENDPOINT_AUTH = "/api/v1/login";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // désactive CSRF pour les API REST
                .authorizeHttpRequests(auth -> auth
                        // Auth + Swagger accessibles sans token
                        .requestMatchers(
                                ENDPOINT_AUTH,
                                ENDPOINTS_PUBLIC,
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                        // Tout le reste protégé
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                ); // Active JWT

        return http.build();
    }
}

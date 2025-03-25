package com.vikas.AirReservation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${travelport.api.url}")
    private String apiUrl;

    @Value("${travelport.auth.username}")
    private String username;

    @Value("${travelport.auth.password}")
    private String password;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeaders(headers -> {
                    headers.setBasicAuth(username, password);
                    headers.set("Content-Type", "text/xml");
                    headers.set("Accept", "application/xml");
                })
                .build();
    }
}
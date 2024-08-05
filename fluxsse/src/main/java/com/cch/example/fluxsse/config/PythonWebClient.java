package com.cch.example.fluxsse.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PythonWebClient {

    @Value("${client.api.endpoint:http://localhost:8000/stream}")
    private String API_ENDPOINT;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(API_ENDPOINT)
                .build();
    }
}
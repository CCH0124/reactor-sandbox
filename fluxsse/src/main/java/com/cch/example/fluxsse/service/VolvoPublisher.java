package com.cch.example.fluxsse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.cch.example.fluxsse.dto.Volvo;

import reactor.core.publisher.Flux;


@Service
public class VolvoPublisher {
    @Autowired
    private WebClient webClient;

    public Flux<Volvo> publish(){
        return this.webClient
                .get()
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Volvo.class)
                .log();
    }
}

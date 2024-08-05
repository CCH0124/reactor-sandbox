package com.cch.example.fluxsse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cch.example.fluxsse.dto.Volvo;
import com.cch.example.fluxsse.service.VolvoPublisher;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.time.LocalTime;

@RestController
public class VolvoController {
    
    @Autowired
    VolvoPublisher volvoPublisher;

    @GetMapping(value = "volvo/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Volvo> getJoke(){
        return volvoPublisher
                .publish()
                .doOnNext(content -> {System.out.println(content);})
                .doFinally(signalType -> System.out.println("Notification flux closed"))
                ;
    }

    @GetMapping(value = "volvo/streamsse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamEvents(){
       return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String> builder()
                    .id(String.valueOf(sequence))
                    .event("periodic-event")
                    .data("SSE - %s".formatted(LocalTime.now().toString())
                )
                .build());
    }
}

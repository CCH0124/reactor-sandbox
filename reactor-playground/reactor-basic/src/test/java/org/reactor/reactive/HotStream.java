package org.reactor.reactive;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class HotStream {
    
    @Test
    public void infinite_stream() {
        ConnectableFlux<Object> publish = Flux.create(sink -> {
            int i = 100_000;
            while (i > 0) {
                sink.next(i--);
            }
        }).publish();

        publish.subscribe(System.out::println); 
        publish.subscribe(System.out::println);
        publish.subscribe(System.out::println);

        publish.connect();
    }
}

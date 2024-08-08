package org.reactor.reactive;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SubscribeTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeTest.class);

    @Test
    public void subscribe_stream_data() {
        List<Integer> eles = new ArrayList<>();
        
        Flux.just(1,2,3,4).log().map(i -> {
            LOGGER.debug("{}:{}", i, Thread.currentThread());
            return i * 2;
        })
        .subscribe(eles::add);

       Assertions.assertIterableEquals(eles, List.of(2, 4, 6, 8));
    }
}

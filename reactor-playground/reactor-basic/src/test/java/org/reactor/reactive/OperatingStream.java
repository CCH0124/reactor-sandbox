package org.reactor.reactive;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import reactor.core.publisher.Flux;

public class OperatingStream {
    @Test
    public void map_test() {
        List<Integer> eles = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 10)
                .log()
                .subscribe(eles::add);

        Assertions.assertIterableEquals(eles, List.of(10, 20, 30, 40));
    }

    @Test
    public void zip_test() {
        List<String> eles = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 10)
                .zipWith(Flux.range(0, Integer.MAX_VALUE),
                        (one, two) -> String.format("First Flux: %d, Second Flux: %d",
                                one, two))
                .subscribe(eles::add);

        Assertions.assertIterableEquals(eles, List.of("First Flux: 10, Second Flux: 0",
                "First Flux: 20, Second Flux: 1",
                "First Flux: 30, Second Flux: 2",
                "First Flux: 40, Second Flux: 3"));
    }

    @Test
    public void zip_increment_test() {
        Flux<Integer> originalFlux = Flux.just(1, 2, 3, 4);
        Flux<Integer> rangeFlux = Flux.range(0, 4);

        Flux.zip(originalFlux, rangeFlux)
        .log()
                .doOnSubscribe(subscription -> System.out.println("onSubscribe: " + subscription))
                .doOnNext(tuple -> {
                    System.out.println("Original: " + tuple.getT1() + ", Range: " + tuple.getT2());
                })
                .doOnComplete(() -> System.out.println("onComplete"))
                .doOnCancel(() -> System.out.println("cancel"))
                .subscribe();
    }
}

package org.reactor.reactive;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Concurrency {
    @Test
    public void test() {
        List<Integer> eles = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
        .log()
        .map(x -> x * x)
        .subscribeOn(Schedulers.parallel())
        .subscribe(eles::add);
    }
}

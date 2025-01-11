package org.reactor.reactive;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;

public class Backpressure {

    List<String> eles = new ArrayList<>();

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test() {
        Flux.just("a", "b", "c", "d")
        .log()
        .subscribe(new Subscriber() {
            private Subscription s;
            int onNextAmount;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(2);
                
            }

            @Override
            public void onNext(Object t) {
                eles.add((String)t);
                onNextAmount++;
                if (onNextAmount % 2 == 0) {
                    s.request(2);
                }
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onComplete() {
            }
            
        });
    }
}

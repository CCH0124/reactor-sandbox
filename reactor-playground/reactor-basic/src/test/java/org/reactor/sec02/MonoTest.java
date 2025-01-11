package org.reactor.sec02;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.reactor.common.Utils;
import org.reactor.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;

public class MonoTest {
    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);

    @Test
    public void justTest() {
        var mono = Mono.just("Itachi");
        var suber = new SubscriberImpl();
        mono.subscribe(suber);

        suber.getSubscription().request(10);
        // adding these wilil have no effect as producer already sent complete.
        suber.getSubscription().request(10);
        suber.getSubscription().cancel();

    }

    @Test
    public void subscribeTest() {
        var mono = Mono.just(10);
        
        mono.subscribe(
            i -> log.info("received: {}", i),
            err -> log.error("error: {}", err),
            () -> log.info("completed."),
            sub -> sub.request(1)
        );
    }

    @Test
    public void subscribe_Error_Test() {
        var mono = Mono.just(10).map(n -> n/0);
        
        mono.subscribe(
            i -> log.info("received: {}", i),
            err -> log.error("error: {}", err),
            () -> log.info("completed."),
            sub -> sub.request(1)
        );

    }

    @Test
    public void empty_error() {
        var mono = Mono.empty();
        var monoErr = Mono.error(new IllegalAccessError("Test"));
        assertNotNull(mono);
        assertThrows(IllegalAccessError.class, () -> Mono.error(new IllegalAccessError("Test")));
    }

    @Test
    public void fromSupply_test() {
        var l = List.of(1, 2, 3);
        Mono.fromSupplier(() -> sum(l))
        .subscribe(x -> log.info("received: {}", x));
    }

    static int sum(List<Integer> l) {
        log.info("list: {}", l);
        return l.stream().mapToInt(x -> x).sum();
    }

    @Test
    public void fromCallback_test() {
        var l = List.of(1, 2, 3);
        Mono.fromCallable(() -> multiple(l))
        .subscribe(x -> log.info("received: {}", x));
    }

    static int multiple(List<Integer> l) throws IllegalArgumentException {
        log.info("list: {}", l);
        return l.stream().mapToInt(x -> x).reduce(1, (x, y) -> x * y);
    }

    @Test
    public void fromRunnable_test() {
        getFoodName("sushi").subscribe(x -> log.info(x));
        getFoodName("unknow").subscribe();
    }

    private static Mono<String> getFoodName(String foodType) {
        return switch (foodType) {
            case "vegetable" ->  Mono.fromSupplier(() -> Utils.instance().food().vegetable());
            case "fruit" ->  Mono.fromSupplier(() -> Utils.instance().food().fruit());
            case "sushi" -> Mono.fromSupplier(() -> Utils.instance().food().sushi());
            default -> Mono.fromRunnable(() -> log.warn("notification food on unavailable product {}", foodType));
        };
    }

    @Test
    public void fromFuture_test() throws InterruptedException {
        Mono.fromFuture(getCompanyName())
        .subscribe(x -> log.info("Company name : {}", x));
        
        Thread.sleep(1000);
    }

    private CompletableFuture<String> getCompanyName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("generating name...");
            return Utils.instance().company().name();
        });
    }
}

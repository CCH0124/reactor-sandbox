package org.reactor.sec01;

import org.junit.jupiter.api.Test;
import org.reactor.sec01.publisher.PublisherImpl;
import org.reactor.sec01.subscriber.SubscriberImpl;

public class Demo {
    /*
     * publisher does not produce data unless subscriber request for it.
     */
    @Test
    public void demo01Test() {
        demo01();
    }

    @Test
    public void demo02Test() {
        demo02();
    }

    /*
     * subscribe can cancel the subscription. produce should stop at the moment as subscribe is no longer interested in consuming the data
     */
    @Test
    public void demo03Test() {
        demo03();
    }

    /*
     * producer can send error single
     */
    @Test
    public void demo04Test() {
        /*
         * this.isCancelled = true; 讓後續訂閱的數量不被再次執行
         */
        demo04();
    }

    private static void demo01() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static void demo02() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
    }

    private static void demo03() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);

    }

    private static void demo04() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        subscriber.getSubscription().request(11);
        subscriber.getSubscription().request(5);

    }
}

package org.events.synchronous;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest
public class EventTest {
    @Autowired
    ApplicationEventPublisher publisher;

    @Test
    public void eventTest() {
        publisher.publishEvent(new CustomEvent(this, "test"));
    }

}

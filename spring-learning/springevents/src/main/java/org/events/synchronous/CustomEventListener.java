package org.events.synchronous;

import java.util.logging.Logger;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {

     private Logger log = Logger.getLogger(CustomEventListener.class.toString());

    @Override
    public void onApplicationEvent(@SuppressWarnings("null") CustomEvent event) {
        log.info("Received spring custom event - " + event.getMessage());
    }
    
}

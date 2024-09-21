package org.events.synchronous;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {

    private String message;

    public CustomEvent(Object source, final String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}

package org.annotation.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class QualifierTest {
    
    @Test
    public void qualifier_test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BikeController bikeController = context.getBean(BikeController.class);
        bikeController.get();
        ((AbstractApplicationContext) context).close();
    }
}

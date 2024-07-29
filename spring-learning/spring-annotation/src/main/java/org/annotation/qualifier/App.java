package org.annotation.qualifier;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BikeController bikeController = context.getBean(BikeController.class);
        bikeController.get();
        ((AbstractApplicationContext) context).close();
    }
}


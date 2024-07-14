/*
 * This source file was generated by the Gradle 'init' task
 */
package org.annotation.autowired;

import org.annotation.autowired.config.AppConfig;
import org.annotation.autowired.controller.CarArgController;
import org.annotation.autowired.controller.CarConstructorController;
import org.annotation.autowired.controller.CarFieldController;
import org.annotation.autowired.controller.CarSetterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CarFieldController userController = context.getBean( CarFieldController.class);
        userController.get();
        ((AbstractApplicationContext) context).close();

        System.out.println("Contructor Injection....");

        context = new AnnotationConfigApplicationContext(AppConfig.class);
        CarConstructorController carConstructorController = context.getBean(CarConstructorController.class);
        carConstructorController.get();
        ((AbstractApplicationContext) context).close();

        System.out.println("Setter Injection........");

        context = new AnnotationConfigApplicationContext(AppConfig.class);
        CarSetterController carSetterController = context.getBean(CarSetterController.class);
        carSetterController.get();
        ((AbstractApplicationContext) context).close();

        System.out.println("Args Injection........");

        context = new AnnotationConfigApplicationContext(AppConfig.class);
        CarArgController carArgController = context.getBean(CarArgController.class);
        carArgController.get();
        ((AbstractApplicationContext) context).close();
    }
}
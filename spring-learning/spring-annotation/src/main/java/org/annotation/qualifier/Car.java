package org.annotation.qualifier;

import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {

    @Override
    public void printBrand() {
        System.out.println("Volvo");
    }

}

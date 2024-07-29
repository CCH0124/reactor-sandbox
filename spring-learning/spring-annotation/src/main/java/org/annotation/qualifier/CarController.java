package org.annotation.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class CarController {
    @Autowired
    @Qualifier("car")
    private Vehicle vehicle;

    public void get() {
        vehicle.printBrand();
        System.out.println("Car Controller Layer");
    }    
}

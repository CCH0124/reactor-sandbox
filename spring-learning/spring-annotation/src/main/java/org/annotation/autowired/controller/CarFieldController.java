package org.annotation.autowired.controller;


import org.annotation.autowired.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CarFieldController {
    @Autowired
    private CarService carService;

    public void get() {
        carService.out();
        System.out.println("Controller Layer");
    }
}

package org.annotation.autowired.controller;
import org.annotation.autowired.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CarArgController {

    private CarService carService;
    
    public CarArgController(@Autowired CarService carService) {
        this.carService = carService;
    }

    public void get() {
        carService.out();
        System.out.println("Controller Layer... Arg injection....................................");
    }
}
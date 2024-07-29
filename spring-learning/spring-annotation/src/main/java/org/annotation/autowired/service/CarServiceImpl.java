package org.annotation.autowired.service;

import org.annotation.autowired.dao.CarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDAO carDAO;

    @Override
    public void out() {
       carDAO.print();
       System.out.println("Service....");
    }
    
}

package org.annotation.autowired.dao;

import org.springframework.stereotype.Component;

@Component
public class CarDaoImpl implements CarDAO{

    @Override
    public void print() {
       System.out.println("Dao...");
    }
    
}

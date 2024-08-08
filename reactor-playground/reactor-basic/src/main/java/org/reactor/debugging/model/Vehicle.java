package org.reactor.debugging.model;
import java.util.UUID;

import io.netty.util.internal.ThreadLocalRandom;


public class Vehicle {

    private String id;
    private String brand;
    private String name;
    private Integer quantity;
    
    public Vehicle(VehicleDto dto) {
        this.id = dto.id();
        this.name = dto.name();
        this.brand = dto.brand();
        this.quantity = randomQuantity();
    }
    
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getBrand() {
        return brand;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    private static String uuidId() {
        return UUID.randomUUID().toString();
    }

    private static int randomQuantity() {
        return ThreadLocalRandom.current().nextInt(0, 10);
    }
}

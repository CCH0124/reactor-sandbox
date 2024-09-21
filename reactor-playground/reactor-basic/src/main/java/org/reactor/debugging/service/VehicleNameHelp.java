package org.reactor.debugging.service;

import org.reactor.debugging.model.Vehicle;

import io.netty.util.internal.ThreadLocalRandom;

public class VehicleNameHelp {

  public static Vehicle concatAndSubstringFooName(Vehicle foo) {
    Vehicle concat = concatBrandName(foo);
    return substringVehicleName(concat);
  }

  public static Vehicle concatBrandName(Vehicle vehicle) {
    int random = ThreadLocalRandom.current()
        .nextInt(0, 80);

    String processedName = (random != 0)
        ? vehicle.getName()
        : vehicle.getName() + vehicle.getBrand();
    vehicle.setName(processedName);
    return vehicle;
  }

  public static Vehicle substringVehicleName(Vehicle vehicle) {
    int random = ThreadLocalRandom.current()
        .nextInt(0, 100);

    String processedName = (random == 0)
        ? vehicle.getName().substring(2, 4)
        : vehicle.getName().substring(0, 5);

    vehicle.setName(processedName);

    return vehicle;
  }

}

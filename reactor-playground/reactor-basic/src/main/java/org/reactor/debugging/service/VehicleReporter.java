package org.reactor.debugging.service;

import org.reactor.debugging.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VehicleReporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleReporter.class);

    public static Vehicle reportResult(Vehicle vehicle, String approach) {
        if (vehicle.getName() != "xc90" || vehicle.getName() != "xc40" || vehicle.getName() != "xc60") {
            throw new IllegalArgumentException("Vehicle name is not valid!");
        }
        LOGGER.info("Reporting for approach {}: Foo with id '{}' name '{}' and quantity '{}'",
          approach, vehicle.getId(), vehicle.getName(), vehicle.getQuantity());

        return vehicle;
    }

    public static Vehicle reportResult(Vehicle input) {
        return reportResult(input, "default");
    }
}

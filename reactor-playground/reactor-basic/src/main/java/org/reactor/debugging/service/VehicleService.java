package org.reactor.debugging.service;

import org.reactor.debugging.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
public class VehicleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleService.class);

    public void processFoo(Flux<Vehicle> flux) {
        flux.map(VehicleNameHelp::concatBrandName)
                .map(VehicleNameHelp::substringVehicleName)
                .log()
                .map(VehicleReporter::reportResult)
                .doOnError(error -> LOGGER.error("The following error happened on processFoo method!", error))
                .subscribe();
    }

    public void processFooVersion2(Flux<Vehicle> flux) {
        flux.map(VehicleNameHelp::concatBrandName)
                .map(VehicleNameHelp::substringVehicleName)
                .log()
                .map(VehicleReporter::reportResult)
                .subscribe(vehicle -> {
                    LOGGER.info("Finish with Id {}", vehicle.getId());
                }, error -> {
                    LOGGER.error("The following error happened on processFoo method!",
                            error);
                });
    }
}

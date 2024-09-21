package org.reactor.debugging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactor.debugging.model.Vehicle;
import org.reactor.debugging.model.VehicleDto;
import org.reactor.debugging.service.VehicleService;

import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

public class VehicleServiceTest {

    VehicleService service = new VehicleService();

    @BeforeEach
    void clearLogList() {
        Hooks.onOperatorDebug();
    }

    @Test
    void givenVehicle_whenProcessFoo_thenLogsWithDebugTrace() {
        Vehicle one = new Vehicle(new VehicleDto(UUID.randomUUID().toString(), "volvo", "xc90"));
        Vehicle two = new Vehicle(new VehicleDto(UUID.randomUUID().toString(), "volvo", "xc95"));
        Flux<Vehicle> flux = Flux.just(one, two);
        service.processFoo(flux);
    }

    @Test
    void givenVehicle_whenProcessFooVersion2_thenLogsWithDebugTrace() {
        Vehicle one = new Vehicle(new VehicleDto(UUID.randomUUID().toString(), "volvo", "xc90"));
        Vehicle two = new Vehicle(new VehicleDto(UUID.randomUUID().toString(), "volvo", "xc90"));
        Flux<Vehicle> flux = Flux.just(one, two);
        service.processFooVersion2(flux);
    }
}

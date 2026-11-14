
package app;

import builder.RentalOrderBuilder;
import factory.CarCreator;
import factory.VehicleCreator;
import model.RentalOrder;
import model.Vehicle;

import java.time.LocalDate;

public class BuilderDemo {

    public static void main(String[] args) {

        // Factory Method: create a vehicle
        VehicleCreator creator = new CarCreator();

        Vehicle car = creator.createAvailableVehicle(
                "V004",
                "Toyota",
                "Camry",
                4000,
                25000,
                150000
        );

        // Builder: create a rental order
        RentalOrder order = new RentalOrderBuilder()
                .orderId("R001")
                .customerName("Alex")
                .vehicle(car)
                .startDate(LocalDate.now())
                .duration(3)
                .pricingType("DAILY")
                .totalPrice(75000)
                .build();

        System.out.println("=== RENTAL ORDER ===");
        System.out.println(order);
    }
}

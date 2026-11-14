
package app;

import factory.*;
import model.Vehicle;

public class FactoryDemo {

    public static void main(String[] args) {

        VehicleCreator carCreator = new CarCreator();
        VehicleCreator motorcycleCreator = new MotorcycleCreator();
        VehicleCreator vanCreator = new VanCreator();

        Vehicle car = carCreator.createAvailableVehicle(
                "V001", "BMW", "320i",
                5000, 30000, 180000
        );

        Vehicle motorcycle = motorcycleCreator.createAvailableVehicle(
                "V002", "Yamaha", "MT-07",
                2000, 12000, 70000
        );

        Vehicle van = vanCreator.createAvailableVehicle(
                "V003", "Mercedes", "Sprinter",
                7000, 40000, 240000
        );

        System.out.println("=== VEHICLE RENTAL SYSTEM ===");

        System.out.println(car);
        System.out.println(motorcycle);
        System.out.println(van);

        System.out.println("\nTotal vehicles created: 3");
    }
}

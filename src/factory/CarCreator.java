
package factory;

import model.Car;
import model.Vehicle;

public class CarCreator extends VehicleCreator {

    @Override
    public Vehicle createVehicle(
            String id, String brand, String model,
            double hourlyRate, double dailyRate, double weeklyRate
    ) {
        return new Car(id, brand, model,
                hourlyRate, dailyRate, weeklyRate);
    }
}

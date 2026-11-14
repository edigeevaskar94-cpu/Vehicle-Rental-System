
package factory;

import model.Motorcycle;
import model.Vehicle;

public class MotorcycleCreator extends VehicleCreator {

    @Override
    public Vehicle createVehicle(
            String id, String brand, String model,
            double hourlyRate, double dailyRate, double weeklyRate
    ) {
        return new Motorcycle(id, brand, model,
                hourlyRate, dailyRate, weeklyRate);
    }
}

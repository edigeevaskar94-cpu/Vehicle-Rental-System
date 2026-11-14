
package factory;

import model.Van;
import model.Vehicle;

public class VanCreator extends VehicleCreator {

    @Override
    public Vehicle createVehicle(
            String id, String brand, String model,
            double hourlyRate, double dailyRate, double weeklyRate
    ) {
        return new Van(id, brand, model,
                hourlyRate, dailyRate, weeklyRate);
    }
}

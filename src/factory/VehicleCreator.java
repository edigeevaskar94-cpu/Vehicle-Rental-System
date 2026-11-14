
package factory;

import model.Vehicle;

public abstract class VehicleCreator {

    public abstract Vehicle createVehicle(
            String id, String brand, String model,
            double hourlyRate, double dailyRate, double weeklyRate
    );

    public Vehicle createAvailableVehicle(
            String id, String brand, String model,
            double hourlyRate, double dailyRate, double weeklyRate
    ) {
        if (id == null || id.isBlank()
                || brand == null || brand.isBlank()
                || model == null || model.isBlank()) {
            throw new IllegalArgumentException("Invalid vehicle information");
        }

        if (!Double.isFinite(hourlyRate) || hourlyRate < 0
                || !Double.isFinite(dailyRate) || dailyRate < 0
                || !Double.isFinite(weeklyRate) || weeklyRate < 0) {
            throw new IllegalArgumentException("Invalid rental rates");
        }

        Vehicle vehicle = createVehicle(
                id, brand, model,
                hourlyRate, dailyRate, weeklyRate
        );

        vehicle.setAvailable(true);
        return vehicle;
    }
}

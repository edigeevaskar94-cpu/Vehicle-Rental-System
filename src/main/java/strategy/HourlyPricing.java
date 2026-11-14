package strategy;

import model.Vehicle;

public class HourlyPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int duration) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive integer");
        }
        return vehicle.getHourlyRate() * duration;
    }

    @Override
    public String getName() {
        return "HOURLY";
    }
}
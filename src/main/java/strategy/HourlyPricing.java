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

        double price = vehicle.getHourlyRate() * duration;
        if (price < 0) {
            throw new ArithmeticException("Calculation overflow or invalid price");
        }
        return price;
    }

    @Override
    public String getName() {
        return "HOURLY";
    }
}
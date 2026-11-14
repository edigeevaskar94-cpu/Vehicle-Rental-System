package strategy;

public class HourlyPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int duration) {
        return vehicle.getHourlyRate() * duration;
    }

    @Override
    public String getName() {
        return "HOURLY";
    }
}
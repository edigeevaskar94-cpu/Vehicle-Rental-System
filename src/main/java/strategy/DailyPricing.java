package strategy;

public class DailyPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int duration) {
        return vehicle.getDailyRate() * duration;
    }

    @Override
    public String getName() {
        return "DAILY";
    }
}
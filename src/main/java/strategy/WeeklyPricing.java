package strategy;

public class WeeklyPricing implements PricingStrategy {
    @Override
    public double calculatePrice(Vehicle vehicle, int duration) {
        return vehicle.getWeeklyRate() * duration;
    }

    @Override
    public String getName() {
        return "WEEKLY";
    }
}
package strategy;

public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, int duration);
    String getName();
}
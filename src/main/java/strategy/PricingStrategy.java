package strategy;

import model.Vehicle;

public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, int duration);
    String getName();
}

package model;

public class Motorcycle extends Car {

    public Motorcycle(String id, String brand, String model,
                      double hourlyRate, double dailyRate, double weeklyRate) {
        super(id, brand, model, hourlyRate, dailyRate, weeklyRate);
    }

    @Override
    public String toString() {
        return "Motorcycle: " + getBrand() + " " + getModel() +
                " | ID: " + getId() +
                " | Available: " + isAvailable();
    }
}

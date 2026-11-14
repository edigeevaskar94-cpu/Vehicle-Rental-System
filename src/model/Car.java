
package model;

public class Car extends AbstractVehicle {

    public Car(String id, String brand, String model,
               double hourlyRate, double dailyRate, double weeklyRate) {
        super(id, brand, model, hourlyRate, dailyRate, weeklyRate);
    }

    @Override
    public String toString() {
        return "Car: " + getBrand() + " " + getModel()
                + " | ID: " + getId()
                + " | Available: " + isAvailable();
    }
}

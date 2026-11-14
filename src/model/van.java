
package model;

public class Van extends Car {

    public Van(String id, String brand, String model,
               double hourlyRate, double dailyRate, double weeklyRate) {
        super(id, brand, model, hourlyRate, dailyRate, weeklyRate);
    }

    @Override
    public String toString() {
        return "Van: " + getBrand() + " " + getModel() +
                " | ID: " + getId() +
                " | Available: " + isAvailable();
    }
}
    
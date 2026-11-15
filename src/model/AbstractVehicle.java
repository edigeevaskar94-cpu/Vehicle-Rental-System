
package model;

public abstract class AbstractVehicle implements Vehicle {

    private final String id;
    private final String brand;
    private final String model;
    private final double hourlyRate;
    private final double dailyRate;
    private final double weeklyRate;
    private boolean available;

    public AbstractVehicle(String id, String brand, String model,
                           double hourlyRate, double dailyRate,
                           double weeklyRate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.weeklyRate = weeklyRate;
        this.available = true;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public double getDailyRate() {
        return dailyRate;
    }

    @Override
    public double getWeeklyRate() {
        return weeklyRate;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }
}

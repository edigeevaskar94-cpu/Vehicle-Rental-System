
package model;

public interface Vehicle {

    String getId();

    String getBrand();

    String getModel();

    double getHourlyRate();

    double getDailyRate();

    double getWeeklyRate();

    boolean isAvailable();

    void setAvailable(boolean available);
}

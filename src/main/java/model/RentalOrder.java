
package model;

import java.time.LocalDate;
import java.util.Objects;

public class RentalOrder {

    private final String orderId;
    private final String customerName;
    private final Vehicle vehicle;
    private final LocalDate startDate;
    private final int duration;
    private final String pricingType;
    private final double totalPrice;

    private OrderStatus status;

    public RentalOrder(String orderId, String customerName,
                       Vehicle vehicle, LocalDate startDate,
                       int duration, String pricingType,
                       double totalPrice) {

        this.orderId = orderId;
        this.customerName = customerName;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.duration = duration;
        this.pricingType = pricingType;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.PENDING;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }

    public String getPricingType() {
        return pricingType;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = Objects.requireNonNull(status);
    }

    @Override
    public String toString() {
        return "Rental Order #" + orderId +
                "\nCustomer: " + customerName +
                "\nVehicle: " + vehicle.getBrand() +
                " " + vehicle.getModel() +
                "\nStart Date: " + startDate +
                "\nDuration: " + duration + " " + pricingType +
                "\nTotal Price: " + totalPrice +
                "\nStatus: " + status;
    }
}

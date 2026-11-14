
package builder;

import model.RentalOrder;
import model.Vehicle;

import java.time.LocalDate;

public class RentalOrderBuilder {

    private String orderId;
    private String customerName;
    private Vehicle vehicle;
    private LocalDate startDate;
    private int duration;
    private String pricingType;
    private double totalPrice;

    public RentalOrderBuilder orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public RentalOrderBuilder customerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public RentalOrderBuilder vehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public RentalOrderBuilder startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public RentalOrderBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    public RentalOrderBuilder pricingType(String pricingType) {
        this.pricingType = pricingType;
        return this;
    }

    public RentalOrderBuilder totalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public RentalOrder build() {

        if (orderId == null || orderId.isBlank()) {
            throw new IllegalStateException("Order ID is required");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalStateException("Customer name is required");
        }

        if (vehicle == null) {
            throw new IllegalStateException("Vehicle is required");
        }

        if (startDate == null) {
            throw new IllegalStateException("Start date is required");
        }

        if (duration <= 0) {
            throw new IllegalStateException("Duration must be positive");
        }

        if (pricingType == null || pricingType.isBlank()) {
            throw new IllegalStateException("Pricing type is required");
        }

        if (!Double.isFinite(totalPrice) || totalPrice < 0) {
            throw new IllegalStateException("Invalid total price");
        }

        return new RentalOrder(
                orderId,
                customerName,
                vehicle,
                startDate,
                duration,
                pricingType,
                totalPrice
        );
    }
}

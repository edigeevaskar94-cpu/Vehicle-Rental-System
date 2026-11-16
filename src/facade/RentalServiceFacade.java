package facade;

import adapter.LegacyPaymentGateway;
import adapter.PaymentGatewayAdapter;
import adapter.PaymentProcessor;
import builder.RentalOrderBuilder;
import factory.CarCreator;
import factory.MotorcycleCreator;
import factory.VanCreator;
import factory.VehicleCreator;
import model.OrderStatus;
import model.RentalOrder;
import model.Vehicle;
import observer.AdminObserver;
import observer.CustomerObserver;
import observer.RentalEventPublisher;
import observer.RentalObserver;
import strategy.DailyPricing;
import strategy.HourlyPricing;
import strategy.PricingStrategy;
import strategy.WeeklyPricing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalServiceFacade {
    private List<Vehicle> vehicles = new ArrayList<>();
    private List<RentalOrder> orders = new ArrayList<>();
    private RentalEventPublisher eventPublisher = new RentalEventPublisher();
    private PaymentProcessor paymentProcessor;
    private int orderCounter = 1;

    public RentalServiceFacade() {
        this.paymentProcessor = new PaymentGatewayAdapter(new LegacyPaymentGateway());
        this.eventPublisher.subscribe(new AdminObserver());
    }

    public void subscribeObserver(RentalObserver observer) {
        eventPublisher.subscribe(observer);
    }

    public Vehicle registerVehicle(String type, String id, String brand, String model,
                                   double hourlyRate, double dailyRate, double weeklyRate) {
        VehicleCreator creator;
        switch (type.toLowerCase()) {
            case "car":
                creator = new CarCreator();
                break;
            case "motorcycle":
                creator = new MotorcycleCreator();
                break;
            case "van":
                creator = new VanCreator();
                break;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }

        Vehicle vehicle = creator.createAvailableVehicle(id, brand, model, hourlyRate, dailyRate, weeklyRate);
        vehicles.add(vehicle);
        return vehicle;
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> available = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                available.add(v);
            }
        }
        return available;
    }

    public RentalOrder createRentalOrder(String customerName, String vehicleId, String pricingType, int duration) {
        Vehicle targetVehicle = findVehicleById(vehicleId);
        if (targetVehicle == null) {
            System.out.println("Error: Vehicle " + vehicleId + " not found.");
            return null;
        }
        if (!targetVehicle.isAvailable()) {
            System.out.println("Error: Vehicle " + vehicleId + " is currently unavailable!");
            return null;
        }

        CustomerObserver customerObserver = new CustomerObserver(customerName);
        eventPublisher.subscribe(customerObserver);

        PricingStrategy strategy;
        switch (pricingType.toLowerCase()) {
            case "hourly":
                strategy = new HourlyPricing();
                break;
            case "weekly":
                strategy = new WeeklyPricing();
                break;
            case "daily":
            default:
                strategy = new DailyPricing();
                break;
        }

        double totalPrice = strategy.calculatePrice(targetVehicle, duration);
        String orderId = String.format("R%03d", orderCounter++);

        RentalOrder order = new RentalOrderBuilder()
                .orderId(orderId)
                .customerName(customerName)
                .vehicle(targetVehicle)
                .startDate(LocalDate.now())
                .duration(duration)
                .pricingType(strategy.getName())
                .totalPrice(totalPrice)
                .build();

        orders.add(order);
        eventPublisher.notifyObservers("Rental Order " + orderId + " created for " + targetVehicle.getBrand() + " " + targetVehicle.getModel() + " (Total: " + totalPrice + ")");

        boolean paymentSuccess = paymentProcessor.processPayment(totalPrice);

        if (paymentSuccess) {
            order.setStatus(OrderStatus.CONFIRMED);
            targetVehicle.setAvailable(false);
            eventPublisher.notifyObservers("Payment Successful! Rental " + orderId + " confirmed! Vehicle " + vehicleId + " is now rented.");
        } else {
            order.setStatus(OrderStatus.PAYMENT_FAILED);
            eventPublisher.notifyObservers("Payment Failed for Rental " + orderId + " (Amount " + totalPrice + " exceeds limit). Vehicle " + vehicleId + " remains available.");
        }

        return order;
    }

    public void cancelRentalOrder(String orderId) {
        RentalOrder order = findOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found: " + orderId);
            return;
        }
        if (order.getStatus() != OrderStatus.CONFIRMED && order.getStatus() != OrderStatus.PENDING) {
            System.out.println("Cannot cancel order in status: " + order.getStatus());
            return;
        }

        order.setStatus(OrderStatus.CANCELLED);
        Vehicle v = order.getVehicle();
        if (!v.isAvailable()) {
            v.setAvailable(true);
        }
        eventPublisher.notifyObservers("Rental " + orderId + " cancelled. Vehicle " + v.getId() + " is available again.");
    }

    public void returnVehicle(String orderId) {
        RentalOrder order = findOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found: " + orderId);
            return;
        }
        if (order.getStatus() != OrderStatus.CONFIRMED) {
            System.out.println("Only CONFIRMED orders can be returned. Current status: " + order.getStatus());
            return;
        }

        order.setStatus(OrderStatus.RETURNED);
        Vehicle v = order.getVehicle();
        v.setAvailable(true);
        eventPublisher.notifyObservers("Vehicle " + v.getId() + " returned (Order " + orderId + "). Vehicle " + v.getId() + " is available again.");
    }

    public List<RentalOrder> getRentalOrders() {
        return orders;
    }

    private Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getId().equalsIgnoreCase(id)) return v;
        }
        return null;
    }

    private RentalOrder findOrderById(String id) {
        for (RentalOrder o : orders) {
            if (o.getOrderId().equalsIgnoreCase(id)) return o;
        }
        return null;
    }
}

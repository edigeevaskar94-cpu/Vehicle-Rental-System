package app;

import facade.RentalServiceFacade;
import model.RentalOrder;
import model.Vehicle;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalServiceFacade facade = new RentalServiceFacade();
        Scanner scanner = new Scanner(System.in);

        runMandatoryDemo(facade);

        while (true) {
            System.out.println("\n===== VEHICLE RENTAL SYSTEM =====");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Available Vehicles");
            System.out.println("3. Create Rental Order");
            System.out.println("4. View Rental Orders");
            System.out.println("5. Cancel Rental");
            System.out.println("6. Return Vehicle");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Type (car/motorcycle/van): ");
                    String type = scanner.nextLine();
                    System.out.print("ID (e.g., V002): ");
                    String id = scanner.nextLine();
                    System.out.print("Brand: ");
                    String brand = scanner.nextLine();
                    System.out.print("Model: ");
                    String model = scanner.nextLine();
                    System.out.print("Hourly rate: ");
                    double hourly = scanner.nextDouble();
                    System.out.print("Daily rate: ");
                    double daily = scanner.nextDouble();
                    System.out.print("Weekly rate: ");
                    double weekly = scanner.nextDouble();
                    facade.registerVehicle(type, id, brand, model, hourly, daily, weekly);
                    System.out.println("Vehicle added successfully!");
                    break;

                case 2:
                    List<Vehicle> available = facade.getAvailableVehicles();
                    System.out.println("--- Available Vehicles ---");
                    for (Vehicle v : available) {
                        System.out.println(v.getId() + ": " + v.getBrand() + " " + v.getModel());
                    }
                    break;

                case 3:
                    System.out.print("Customer Name: ");
                    String customer = scanner.nextLine();
                    System.out.print("Vehicle ID: ");
                    String vId = scanner.nextLine();
                    System.out.print("Pricing (hourly/daily/weekly): ");
                    String pricing = scanner.nextLine();
                    System.out.print("Duration: ");
                    int duration = scanner.nextInt();
                    facade.createRentalOrder(customer, vId, pricing, duration);
                    break;

                case 4:
                    System.out.println("--- Rental Orders ---");
                    for (RentalOrder o : facade.getRentalOrders()) {
                        System.out.println(o.getOrderId() + " | " + o.getStatus());
                    }
                    break;

                case 5:
                    System.out.print("Enter Order ID to cancel: ");
                    String cancelId = scanner.nextLine();
                    facade.cancelRentalOrder(cancelId);
                    break;

                case 6:
                    System.out.print("Enter Order ID to return: ");
                    String returnId = scanner.nextLine();
                    facade.returnVehicle(returnId);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void runMandatoryDemo(RentalServiceFacade facade) {
        System.out.println("========== RUNNING MANDATORY DEMO ==========");

        System.out.println("\n1. Creating Toyota Camry...");
        facade.registerVehicle("car", "V001", "Toyota", "Camry", 1000, 3000, 18000);

        System.out.println("\n2. Available Vehicles:");
        facade.getAvailableVehicles().forEach(v -> System.out.println(" - " + v.getId() + " " + v.getBrand() + " " + v.getModel()));

        System.out.println("\n3. Creating Rental Order for Alex (3 days, DailyPricing):");
        RentalOrder order1 = facade.createRentalOrder("Alex", "V001", "daily", 3);

        System.out.println("\n4. Trying to rent V001 again while it is rented:");
        facade.createRentalOrder("John", "V001", "daily", 1);

        System.out.println("\n5. Returning Toyota Camry:");
        if (order1 != null) {
            facade.returnVehicle(order1.getOrderId());
        }

        System.out.println("\n6. Testing Failed Payment (Amount > 10000):");
        facade.createRentalOrder("Maria", "V001", "daily", 5);

        System.out.println("\n========== DEMO FINISHED ==========\n");
    }
}
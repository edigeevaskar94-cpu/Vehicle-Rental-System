package strategy;

import adapter.LegacyPaymentGateway;
import adapter.PaymentGatewayAdapter;
import adapter.PaymentProcessor;
import model.Vehicle;

public class StrategyAdapterDemo {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ STRATEGY & ADAPTER ===");

        // Заглушка Vehicle для теста
        Vehicle mockVehicle = new Vehicle() {
            @Override public String getId() { return "V-01"; }
            @Override public String getBrand() { return "Toyota"; }
            @Override public String getModel() { return "Camry"; }
            @Override public double getHourlyRate() { return 15.0; }
            @Override public double getDailyRate() { return 120.0; }
            @Override public double getWeeklyRate() { return 700.0; }
            @Override public boolean isAvailable() { return true; }
            @Override public void setAvailable(boolean a) {}
        };

        // 1. Почасовой тариф
        PricingStrategy hourly = new HourlyPricing();
        System.out.println("1. Hourly: " + hourly.calculatePrice(mockVehicle, 4) + " [" + hourly.getName() + "]");

        // 2. Дневной тариф
        PricingStrategy daily = new DailyPricing();
        System.out.println("2. Daily: " + daily.calculatePrice(mockVehicle, 3) + " [" + daily.getName() + "]");

        // 3. Недельный тариф
        PricingStrategy weekly = new WeeklyPricing();
        System.out.println("3. Weekly: " + weekly.calculatePrice(mockVehicle, 2) + " [" + weekly.getName() + "]");

        // 4. Успешная оплата через Adapter
        PaymentProcessor paymentProcessor = new PaymentGatewayAdapter(new LegacyPaymentGateway());
        boolean success = paymentProcessor.processPayment(250.0);
        System.*out*.println("4. Successful Payment: " + success);

        // 5. Неуспешная оплата через Adapter
        boolean failed = paymentProcessor.processPayment(-50.0);
        System.*out*.println("5. Failed Payment: " + failed);
    }
}
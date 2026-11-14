package adapter;

public class PaymentGatewayAdapter implements PaymentProcessor {
    private final LegacyPaymentGateway legacyGateway;

    public PaymentGatewayAdapter(LegacyPaymentGateway legacyGateway) {
        this.legacyGateway = legacyGateway;
    }

    @Override
    public boolean processPayment(double amount) {
        // Преобразуем вызов интерфейса PaymentProcessor в метод старой системы
        return legacyGateway.makeTransaction(amount);
    }
}
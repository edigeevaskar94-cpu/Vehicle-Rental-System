package adapter;

public class PaymentGatewayAdapter implements PaymentProcessor {
    private final LegacyPaymentGateway legacyGateway;
    private final String customerName;

    public PaymentGatewayAdapter(LegacyPaymentGateway legacyGateway, String customerName) {
        this.legacyGateway = legacyGateway;
        this.customerName = customerName;
    }

    @Override
    public boolean processPayment(double amount) {
        // Конвертируем рубли/доллары в центы (умножаем на 100)
        int amountInCents = (int) Math.round(amount * 100);
        return legacyGateway.makeTransaction(customerName, amountInCents);
    }
}
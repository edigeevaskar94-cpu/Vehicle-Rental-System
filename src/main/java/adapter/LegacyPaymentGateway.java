package adapter;

public class LegacyPaymentGateway {
    public boolean makeTransaction(String customerInfo, int amountInCents) {
        System.out.println("[LegacyPaymentGateway] Payment processing: " + amountInCents + " cents for " + customerInfo);
        return true;
    }
}
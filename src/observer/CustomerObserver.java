package observer;

public class CustomerObserver implements RentalObserver {
    private String customerName;

    public CustomerObserver(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public void update(String message) {
        System.out.println("[Customer - " + customerName + "] " + message);
    }
}
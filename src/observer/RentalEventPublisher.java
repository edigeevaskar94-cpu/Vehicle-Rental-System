package observer;

import java.util.ArrayList;
import java.util.List;

public class RentalEventPublisher {
    private List<RentalObserver> observers = new ArrayList<>();

    public void subscribe(RentalObserver observer) {
        if (observer instanceof CustomerObserver) {
            String newName = ((CustomerObserver) observer).getCustomerName();
            for (RentalObserver obs : observers) {
                if (obs instanceof CustomerObserver &&
                        ((CustomerObserver) obs).getCustomerName().equalsIgnoreCase(newName)) {
                    return;
                }
            }
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unsubscribe(RentalObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (RentalObserver observer : observers) {
            observer.update(message);
        }
    }

    public void notifyCustomerAndAdmin(String customerName, String message) {
        for (RentalObserver observer : observers) {
            if (observer instanceof AdminObserver) {
                observer.update(message);
            } else if (observer instanceof CustomerObserver) {
                CustomerObserver customer = (CustomerObserver) observer;
                if (customer.getCustomerName().equalsIgnoreCase(customerName)) {
                    customer.update(message);
                }
            }
        }
    }
}
package observer;

import java.util.ArrayList;
import java.util.List;

public class RentalEventPublisher {
    private List<RentalObserver> observers = new ArrayList<>();

    public void subscribe(RentalObserver observer) {
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
}
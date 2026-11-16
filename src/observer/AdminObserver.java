package observer;

public class AdminObserver implements RentalObserver {
    @Override
    public void update(String message) {
        System.out.println("[Admin] " + message);
    }
}
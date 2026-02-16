package patterns;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    public static void main(String[] args) {
        MessagePublisher publisher = new MessagePublisher();
        publisher.subscribe(new EmailObserver());
        publisher.subscribe(new SmsObserver());
        publisher.publish("Hello");
    }
}

interface Observer {
    default void observe(String message) {
        System.out.printf("%s:: Observing %s\n", this.getClass().getSimpleName(), message);
    }
}

class EmailObserver implements Observer { }
class SmsObserver implements Observer { }

class MessagePublisher {
    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        this.observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        this.observers.remove(observer);
    }

    public void publish(String message) {
        for (Observer observer : observers) {
            observer.observe(message);
        }
    }
}

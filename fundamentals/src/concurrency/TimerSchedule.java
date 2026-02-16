package concurrency;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerSchedule {
    public static void main(String[] args) throws InterruptedException {
        try (ScheduledExecutorService s = Executors.newScheduledThreadPool(1)) {
            s.scheduleAtFixedRate(() -> System.out.println("1 Second"), 0, 1, TimeUnit.SECONDS);
            Thread.sleep(5000);
        }
    }
}

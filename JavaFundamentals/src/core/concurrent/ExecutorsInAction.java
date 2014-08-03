package core.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorsInAction {
	public static void main(String[] args) throws Exception {
		demoThreadPoolDirect();
		demoFixedThreadPool();
		scheduleFixed();
	}

	private static void demoThreadPoolDirect() throws Exception {
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), threadFactory, handler);
		executor.submit(()-> System.out.println("Fixed threadpool"));
		
		executor.shutdownNow();
	}
	
	private static void demoFixedThreadPool() throws InterruptedException {
		ExecutorService fixedThreadExecutor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 10; i++) {
			Integer id = new Integer(i);
			fixedThreadExecutor.execute(() -> System.out.println("Hello --> " + id));
		}
		
		fixedThreadExecutor.shutdown();
		if (!fixedThreadExecutor.awaitTermination(100, TimeUnit.MILLISECONDS)) {
			fixedThreadExecutor.shutdownNow();
		}
	}
	
	
	private static void scheduleFixed() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.scheduleAtFixedRate(() -> System.out.println("Hello"), 0, 1, TimeUnit.SECONDS);
		executor.scheduleWithFixedDelay(() -> System.out.println("Hello"), 0, 1, TimeUnit.SECONDS);
		
	}
}

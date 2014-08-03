package core;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Synchronizers {
	public static void main(String[] args) throws Exception {
		demoCyclicBarrier();
	}

	private static void demoCyclicBarrier() {
		CyclicBarrier barrier = new CyclicBarrier(3);
		ExecutorService executor = Executors.newFixedThreadPool(5);
		IntStream.range(0, 3).forEach( e -> {
			executor.submit(() -> {
				System.out.println("Arrived at Barrier " + e);
				barrier.await();
				System.out.println("Crossed the Barrier " + e);
				return null;
			});
		});
		executor.shutdown();
	}
}

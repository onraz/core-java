package core.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Synchronizers {
	public static void main(String[] args) throws Exception {
		demoCyclicBarrier();
//		demoCountdownlatch();
//		demoRentrantLock();
	}

	
	// TODO: Demo Semaphores, Exchanger
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
		// once barrier is used, it no longer can block threads, need to reset
		barrier.reset();
		executor.shutdown();
		
		/**
		 * Executes the task when all in barrier
		 */
		CyclicBarrier barrierWithTask = new CyclicBarrier(3, () -> System.out.println("YAY! All crossed the barrier"));
		ExecutorService executor2 = Executors.newFixedThreadPool(5);
		IntStream.range(0, 3).forEach( e -> {
			executor2.submit(() -> {
				System.out.println("Arrived at Barrier " + e);
				barrierWithTask.await();
				return null;
			});
		});
		// once barrier is used, it no longer can block threads, need to reset
		barrierWithTask.reset();
		executor2.shutdown();
	}
	
	private static void demoCountdownlatch() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executor = Executors.newCachedThreadPool();
		
		// submit three tasks
		IntStream.range(0, 3).forEach( e -> {
			executor.submit(() -> {
				// do long running task here
				System.out.println("Performing long task: " + e);
				// once task is finished, countDown to signal latch
				latch.countDown();
			});
		});
		System.out.println("Waiting till all tasks are done.....");
		latch.await();
		System.out.println("All tasks are done!! ");
		
		executor.shutdown();
	}
	

	private static void demoRentrantLock() throws InterruptedException {
		Lock lock = new ReentrantLock();
		lock.lock();
		Condition notDone = lock.newCondition();
		ExecutorService executor = Executors.newFixedThreadPool(5);
		IntStream.range(0, 3).forEach( e -> {
			executor.submit(() -> {
				try {
					System.out.println("Waiting to acquire lock " + e);
					lock.lock();
					System.out.println("Acquired lock " + e);
					Thread.sleep(5000);
					System.out.println("Finished task " + e);
				} finally {
					if (e == 2) { 
						// signal while holding lock
						System.out.println("Resume main " + e);
						notDone.signal();
						System.out.println("Signal Sent " + e);
						Thread.sleep(5000);
					}
					System.out.println("Unlocking " + e);
					lock.unlock();
				}
				return null;
			});
		});
		System.out.println("Waiting till all tasks are done.....");
		notDone.await();
		System.out.println("YAY! All tasks are done!! ");
		lock.unlock();
		executor.shutdown();
	}	
}

package core.collection;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrencyCollections {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CountDownLatch latch = new CountDownLatch(10);
		List<Future<String>> list = new CopyOnWriteArrayList<>();
		Map<Double, String> map = new ConcurrentHashMap<>();
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			Future<String> result = executor.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep(1000);
					latch.countDown();
					double d = Math.random() * 1000;
					String value = " " + d;
					map.put(d, value);
					return value;
				}
			});
			list.add(result);
		}
		executor.shutdown();
		latch.await();
		
		for (Future<String> task : list) {
			System.out.println(task.get());
		}
		
		for (Map.Entry<Double, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "==" + entry.getValue());
		}
	}

}

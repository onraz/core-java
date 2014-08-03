package core.concurrent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceDemo {

        private static final int waittime = 200;
        private static final int numberOfThreadsInThePool = 3;

        private final List<String> printRequests = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
                        );

        void normalLoop() {
                for (String image : printRequests) {
                        try {
                                Thread.sleep(waittime);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        System.out.print(image);
                }
        }

        void normalExecutorService() {
                ExecutorService executor = Executors.newFixedThreadPool(numberOfThreadsInThePool);
                try {
                        Set<Future<String>> printTaskFutures = new HashSet<Future<String>>();
                        for (final String printRequest : printRequests) {
                                printTaskFutures.add(executor.submit(new Printer(printRequest)));
                        }
                        for (Future<String> future : printTaskFutures) {
                                System.out.print(future.get());

                        }
                } catch (Exception e) {
                        Thread.currentThread().interrupt();
                } finally {
                        if (executor != null) {
                                executor.shutdownNow();
                        }
                }
        }

        void completionService() {
                ExecutorService executor = Executors.newFixedThreadPool(numberOfThreadsInThePool);
                CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
                for (final String printRequest : printRequests) {
                        completionService.submit(new Printer(printRequest));
                }
                try {
                        for (int t = 0, n = printRequests.size(); t < n; t++) {
                                Future<String> f = completionService.take();
                                System.out.print(f.get());
                        }
                } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                } catch (ExecutionException e) {
                        Thread.currentThread().interrupt();
                } finally {
                        if (executor != null) {
                                executor.shutdownNow();
                        }
                }

        }

        private class Printer implements Callable<String> {

                private final String toPrint;

                public Printer(String toPrint) {
                        this.toPrint = toPrint;
                }

                public String call() {
                        try {
                                Thread.sleep(waittime);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        return toPrint;
                }
        }

        public static void main(String[] args) {
                System.out.println("Normal Executor Service");
                long start = System.currentTimeMillis();
                new CompletionServiceDemo().normalExecutorService();
                System.out.println();
                System.out.println("Execution time : " + (System.currentTimeMillis() - start));

                System.out.println("Completion Service");
                start = System.currentTimeMillis();
                new CompletionServiceDemo().completionService();
                System.out.println();
                System.out.println("Execution time : " + (System.currentTimeMillis() - start));

                System.out.println("Normal Loop");
                start = System.currentTimeMillis();
                new CompletionServiceDemo().normalLoop();
                System.out.println();
                System.out.println("Execution time : " + (System.currentTimeMillis() - start));

        }
}

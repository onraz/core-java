package queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class MovingAverage {
    static Queue<Integer> queue = new ArrayDeque<>(2);
    public static int movingAvg(int[] input) {
        int avg = 0;
        for (int num : input) {
            if (!queue.offer(num)) {
            }

        }
        return avg;
    }
}

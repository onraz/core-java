package array_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] array = {1, -2, 3, 5};
        System.out.println(Arrays.toString(twoSumUnsorted(array, 3)));
        System.out.println(Arrays.toString(twoSumCompUnsorted(array, 3)));
    }

    public static int[] twoSumUnsorted(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if ((numbers[i] + numbers[j]) == target) {
                    return new int[] {numbers[i], numbers[j]};
                }
            }
        }
        return new int[]{};
    }

    public static int[] twoSumCompUnsorted(int[] numbers, int target) {
        Map<Integer, Integer> complement = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
                int comp = target - numbers[i];
                if (complement.containsKey(comp)) {
                    return new int[]{numbers[i], numbers[complement.get(comp)]};
                }
                complement.put(numbers[i], i);
        }
        return new int[]{};
    }

    public static int[] twoSumSorted(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                return new int[] {nums[L], nums[R]};
            }
        }
        return new int[]{};
    }
}

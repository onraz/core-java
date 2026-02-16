package array_sum;

/**
 * Find Maximal Sum (Contiguous Subarray) - given goal is to find max sum -
 *  For a contiguous subarray [ a, b, c, d, e ] == [ a+b, c, d, e]
 *  so the elements a, b can be replaced with a+b as currentSum
 *  But if a+b is negative, it won't contribute to the maxSum, so we
 *  can eliminate it and imagine array to be just [c, d, e]
 *
 *  As we go through this array in this way tracking currentSum, we will get local maxima and minimas,
 *  as the array is not sorted, and there will be positive and negative numbers
 *
 *  So we just find the maxof local maximas.
 *
 *  - Forward Drive - Accumulate & Filter only those who improve the Sum
 *  -
 */
public class MaxSumSubArrayAlgo {
    public static void main(String[] args) {
        int[] input ={ 4, -2, 2, -1, 2, -1, 3, -8};
        int maxSum = kadaneAlgoForMaxSum(input);
        System.out.println(maxSum);
    }

    private static int kadaneAlgoForMaxSum(int[] input) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = input[0];
        for (int i = 1; i < input.length; i++) {
            currentSum = Math.max(input[i], currentSum + input[i]);
            maxSum = Math.max(currentSum, maxSum);
        }
        return maxSum;
    }
}

package array_sum;

import java.util.Arrays;

/**
 Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper,
 return the researcher's h-index.
 According to the definition of h-index on Wikipedia:
 The h-index is defined as the maximum value of h such that:
    the given researcher has published at least h papers (papers >= h)
    that have each been cited at least h times. (i.e. citations[i] >= h)

 Example 1:
    -Input: citations = [3,0,6,1,5]
    -Output: 3

    Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
    received 3, 0, 6, 1, 5 citations respectively.
    Since the researcher has 3 papers with at least 3 citations each and the remaining two with
    no more than 3 citations each, their h-index is 3.

 Example 2:
    Input: citations = [1,3,1]
    Output: 1

 */
public class HIndex {
    public static void main(String[] args) {
        int[] input = {1, 3, 1};
        System.out.print(" input " + Arrays.toString(input));
        System.out.println(" hIndex " + hIndex(input));
        input = new int[]{3,0,6,1,5};
        System.out.print(" input " + Arrays.toString(input));
        System.out.println(" hIndex " + hIndex(input));
        input = new int[]{-1};
        System.out.print(" input " + Arrays.toString(input));
        System.out.println(" hIndex " + hIndex(input));
        input = new int[]{0};
        System.out.print(" input " + Arrays.toString(input));
        System.out.println(" hIndex " + hIndex(input));
        input = new int[]{99};
        System.out.print(" input " + Arrays.toString(input));
        System.out.println(" hIndex " + hIndex(input));
    }

    public static int hIndex(int[] citations) {
        int hIndex = citations.length;
        for (; hIndex > 0; hIndex--) {
            int papers = 0;
            for (int c : citations) {
                if (c >= hIndex) papers++;
            }
            if (papers >= hIndex) break;
        }
        return hIndex;
    }
}

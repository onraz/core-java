package subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**

Programming Problem (~40 min)

Given a list of transactions, How can we calculate the frequency
counts of all possible combinations of item-sets?

For example,
[GIVEN] Item types in inventory:  apple, banana, berry, lemon, orange
[INPUT] list of transactions
| -- | -----------------------------|
| ID | Purchased items              |
| -- | -----------------------------|
| 1  | apple, banana, lemon         |
| 2  | banana, berry, lemon, orange |
| 3  | banana, berry, lemon         |
| ...                               |
| -- | -----------------------------|


[OUTPUT] frequency counts of all possible item-sets. Note: some outputs are omitted for brevity.
| ---------------------------- | --------- |
| Itemset                      | Frequency |
| ---------------------------- | --------- |
| apple, banana                | 1         |
| apple, lemon                 | 1         |
| banana, berry                | 2         |
| banana, lemon                | 3         |
| ...                                      |
| apple, banana, lemon         | 1         |
| banana, berry, lemon         | 2         |
| ...                                      |
| banana, berry, lemon, orange | 1         |
| ...                                      |
| ---------------------------- | --------- |

*/
public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        System.out.println(powerSet(Arrays.asList("apple", "banana", "lemon")));
        List<List<String>> transactions = Arrays.asList(
                Arrays.asList("apple", "banana", "lemon")
        );
    }

    // [a b] [a l] [b l] [a b l]


    static List<ItemSetFreq> findItemSets(List<Txn> txns) {
        Map<ItemSet, Integer> map = new HashMap<>();
        for (Txn txn : txns) {
            List<HashSet<String>> powerSet = powerSet(txn.items);
            for (HashSet<String> set : powerSet) {
                ItemSet itemSet = new ItemSet(set);
                map.put(itemSet, map.getOrDefault(itemSet, 0) + 1);
            }
        }

        List<ItemSetFreq> result = new ArrayList<>();
        for (ItemSet itemSet : map.keySet()) {
            result.add(new ItemSetFreq(itemSet.items, map.get(itemSet)));
        }
        return result;
    }

    // apple, banana, lemon
    static List<HashSet<String>> powerSet(List<String> list) {
        List<HashSet<String>> result = new ArrayList<>();
        powerSet(list, 0, new HashSet<String>(), result);
        return result;
    }

    // list = [apple, banana, lemon]
    // start = 2
    // solution = []
    static void powerSet(List<String> list, int start, HashSet<String> solution, List<HashSet<String>> result) {
        if (solution.size() > 1) {
            result.add(new HashSet<>(solution));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            powerSet(list, i+1, solution, result);


            solution.remove(list.get(i));
            powerSet(list, i + 1, solution, result);
            solution.add(list.get(i));
        }
    }

    static class Txn {
        int id;
        List<String> items;
    }

    static class ItemSet {
        HashSet<String> items;

        ItemSet(HashSet<String> items) {
            this.items = items;
        }

        public boolean equals(Object other) {
            if (this == other) return true;
            if (getClass() != other.getClass()) return false;
            ItemSet that = (ItemSet) other;
            return items.equals(that.items);
        }

        public int hashCode() {
            return items.hashCode();
        }
    }

    static class ItemSetFreq {
        HashSet<String> items;
        int frequency;

        ItemSetFreq(HashSet<String> items, int frequency) {
            this.items = items;
            this.frequency = frequency;
        }
    }
}
package subset;

/**

     Programming Problem (~40 min)

     The aim of this problem is to help a grocery owner to better plan
     inventory and discounts to customers who purchase multiple items
     together.

     Given a list of transactions, How can we calculate the frequency
     counts of all possible combinations of item-sets?

     For example,

     [GIVEN] Inventory is:  apple, banana, berry, lemon, orange.

     [INPUT] list of transactions
     | -- | -----------------------------|
     | ID | Purchased items              |
     | -- | -----------------------------|
     | 1  | apple, banana, lemon         |
     | 2  | banana, berry, lemon, orange |
     | 3  | banana, berry, lemon         |
     | ...                               |
     | -- | -----------------------------|


     [OUTPUT] frequency counts of all possible item-sets.
     Note: some outputs are omitted for brevity.
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

public class Question {

}

"""
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.

"""

def find_max_profit(input):
    max_profit = 0
    for i in range(len(input) - 1):
        for j in range(i + 1, len(input)):
            if input[j] > input[i]:
                max_profit = max(max_profit, input[j] - input[i])

    return max_profit


"""
Key: Only forward drive
The points of interest are the peaks and valleys in the given graph. 
- minimum price so far
- max profit can only be in terms of min price so far (to the left, deepest valley) from current
- when we are in current we already know the lowest to the left
- once we are past current we don't care if there is another lowest to the right
We need to find the largest price following each valley, which difference could be the max profit.

We can maintain two variables - minprice and maxprofit corresponding to the smallest valley and maximum profit 
(maximum difference between selling price and minprice) obtained so far respectively.
"""
def find_max_profit2(input):
    min_price = input[0]
    max_profit = 0
    for i in range(1, len(input)):
        if input[i] < min_price:
            min_price = input[i]
        elif input[i] - min_price > max_profit:
            max_profit = input[i] - min_price
    return max_profit

print(find_max_profit2([7,1,5,3,6,4]))
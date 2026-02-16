def coinChange(coins, amount):
    def dfs(coins, remain):
        if remain < 0:
            return -1  # Cannot make up a negative amount
        if remain == 0:
            return 0   # Do not need to choose a coin

        min_count = float('inf')

        for coin in coins:
            count = dfs(coins, remain - coin)
            if count != -1:
                min_count = min(min_count, count + 1)

        return -1 if min_count == float('inf') else min_count

    return dfs(coins, amount)


def coin_change(coins, amount):
    memo = {}
    return dfs(coins, amount, memo)

def dfs(coins, remain, memo):
    if remain < 0:
        return -1
    if remain == 0:
        return 0
    if remain in memo:
        return memo[remain]

    min_count = float('inf')
    for coin in coins:
        count = dfs(coins, remain-coin, memo)
        if count != -1:
            min_count = min(min_count, count+1)

    memo[remain] = min_count if min_count != float('inf') else -1
    return memo[remain]


def dyncoin(coins, amount):
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0

    for a in range(1, amount+1):
        for c in coins:
            dp[a] = min(dp[a], dp[a-c]+1)

    return dp[amount] if dp[amount] != float('inf') else -1

print(coinChange([1, 2, 5], 11))  # Output: 3  (5 + 5 + 1)
print(coinChange([2], 3))         # Output: -1
print(coinChange([1], 0))         # Output: 0

print(coin_change([1, 2, 5], 11))  # Output: 3  (5 + 5 + 1)
print(coin_change([2], 3))         # Output: -1
print(coin_change([1], 0))         # Output: 0

print(dyncoin([1, 2, 5], 11))  # Output: 3  (5 + 5 + 1)
print(dyncoin([2], 3))         # Output: -1
print(dyncoin([1], 0))         # Output: 0
import collections


class Solution:
    def minTransfers(self, transactions: list[list[int]]) -> int:
        # Step 1: Calculate net balances for each person
        balances = collections.defaultdict(int)
        for u, v, amount in transactions:
            balances[u] -= amount
            balances[v] += amount

        # Step 2: Filter out people with a zero balance
        debts = [balance for balance in balances.values() if balance != 0]

        # Step 3: Use DFS with backtracking to find the minimum transactions
        def dfs(start_index):
            # Skip settled accounts
            while start_index < len(debts) and debts[start_index] == 0:
                start_index += 1

            # Base case: All debts are settled
            if start_index == len(debts):
                return 0

            ans = float('inf')

            # Find a person to pay/receive
            for i in range(start_index + 1, len(debts)):
                # Only consider transactions between opposite balances
                if debts[start_index] * debts[i] < 0:
                    # Make a transaction
                    debts[i] += debts[start_index]

                    # Recurse and count this transaction
                    ans = min(ans, 1 + dfs(start_index + 1))

                    # Backtrack: undo the transaction
                    debts[i] -= debts[start_index]
            return ans

        return dfs(0)

from collections import defaultdict

input = "a+b/c*d-e"


def priority(op):
    return 1 if op in "*/" else 0


def pfix(expr):
    stack = []
    res = ""
    for ch in expr:
        if ch in "+-/*":
            while stack and priority(stack[-1]) >= priority(ch):
                res += stack.pop()
            stack.append(ch)
        else:
            res += ch

    while stack:
        res += stack.pop()

    return res

# print(pfix(input))



def minTransfers(transactions):

    score = defaultdict(int)
    for f, t, a in transactions:
        score[f] -= a
        score[t] += a
    print(score.items())
    positives = [v for v in score.values() if v > 0]
    negatives = [v for v in score.values() if v < 0]
    print(positives)
    print(negatives)

    def recurse(positives, negatives):

        if len(positives) + len(negatives) == 0:
            return 0

        negative = negatives[0]
        print(f"Settling debt {negative}")
        count = float('inf')
        for positive in positives:
            new_positives = positives.copy()
            new_negatives = negatives.copy()

            remain = positive + negative
            print(f"    Evaluating {positive} + {negative} ==> {remain}")
            if remain == 0:
                print(f"    matched {positive}, {negative}")
                new_positives.remove(positive)
                new_negatives.remove(negative)
            elif remain > 0:
                new_positives.remove(positive)
                new_positives.append(remain)
                new_negatives.remove(negative)
            else:
                new_positives.remove(positive)
                new_negatives.remove(negative)
                new_negatives.append(remain)

            print(f"    New positive {new_positives}, negative {new_negatives}")
            c = recurse(new_positives, new_negatives)
            print(f"    New positive {new_positives}, negative {new_negatives} <== got count {c}, current count {count}, thus count= {min(count,c)}")
            count = min(count, c)
        return count + 1

    return recurse(positives, negatives)



# print(minTransfers([[0,1,10],[2,0,5]]))
#
# def transfers(txn):
#     balances = defaultdict(int)
#
#     for s, d, amt in txn:
#         balances[s] -= amt
#         balances[d] += amt
#
#     debts = [val for val in balances.values() if val != 0]
#     names = [name for name, val in balances.items() if val != 0]
#
#     def dfs(person, res):
#         # skip a settled person
#         while person < len(debts) and debts[person] == 0:
#             person += 1
#
#         if person == len(debts):
#             return []
#
#         # everyone is settled prior to person
#         for i in range(person + 1, len(debts)):
#             if debts[i] * debts[person] < 0:
#                 amt = debts[i]
#                 debts[i] -= debts[person]
#                 res.append([i, person, amt])
#                 if dfs(person + 1, res) is not None:
#                     return res
#                 res.pop()
#                 debts[i] += debts[person]
#         return None
#
#     res = dfs(0, [])
#     return res
    # Compute net balance for each person
    # for frm, to, amt in transactions:
    #     balance[frm] -= amt
    #     balance[to] += amt
    #
    # # Filter out zero balances
    # debts = [v for v in balance.values() if v != 0]
    #
    # def dfs(i):
    #     # Skip settled (zero) entries
    #     while i < len(debts) and debts[i] == 0:
    #         i += 1
    #     if i == len(debts):
    #         return []  # everyone settled
    #
    #     for j in range(i + 1, len(debts)):
    #         # Opposite signs mean potential settlement
    #         if debts[i] * debts[j] < 0:
    #             # Try settling i with j
    #             debts[j] += debts[i]
    #             result = dfs(i + 1)
    #             if result is not None:
    #                 return [(i, j, -debts[i])] + result
    #             debts[j] -= debts[i]  # backtrack
    #     return None
    #
    # res = dfs(0)
    # if not res:
    #     return []
    #
    # # Convert indices back to names
    # names = [k for k, v in balance.items() if v != 0]
    # return [(names[i], names[j], amt) for i, j, amt in res]

from collections import defaultdict

def settle_debtsc(transactions):
    balances = defaultdict(int)

    for s, d, amt in transactions:
        balances[s] -= amt
        balances[d] += amt

    print(balances.items())
    debt = [val for val in balances.values() if val != 0]

    def dfs(person, path):
        # skip person's debt
        while person < len(debt) and debt[person] == 0:
            person += 1

        if person == len(debt):
            return path

        for other in range(person+1, len(debt)):
            if debt[other] * debt[person] < 0:
                amt = debt[person] + debt[other]
                if debt[person] > debt[other]:
                    path.append((person, other, abs(debt[person])))
                else:
                    path.append((other, person, abs(debt[person])))
                debt[other] = amt
                if dfs(person + 1, path) is not None:
                    return path

                path.pop()
                debt[other] -= debt[person] # backtrack
        return None

    return dfs(0, [])

print(settle_debtsc([[0,1,10],[2,0,5]]))





def two_sum(input: list[int], target: int) -> list[int]:
    input.sort()
    L, R = 0, len(input) -1
    while L < R:
        if input[L] + input[R] > target:
            R -= 1
        elif input[L] + input[R] < target:
            L += 1
        else:
            return [input[L], input[R]]
    return []

def two_sum_comp(input: list[int], target: int) -> list[int]:
    complements = {}
    for i, num in enumerate(input):
        comp = target - num
        if complements.get(comp):
            return [comp, num]
        else:
            complements[num] = i
    return []


def two_sum_brute(input, target):
    for i in range(0, len(input)):
        for j in range(i, len(input)):
            if input[i] + input[j] == target:
                return [input[i], input[j]]
    return []

data = [1, -2, 3, 5]
print(two_sum(data, 3))
print(two_sum_brute(data, 3))
print(two_sum_comp(data, 3))
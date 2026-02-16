from typing import List

# Brute force
def two_sum(nums: List[int], target: int) -> List[int]:
    for i in range(len(nums)):
        for j in range(i+1, len(nums)):
            sum = nums[i] + nums[j]
            if sum == target:
                return [i, j]
    return []

# Brute force
def two_sum_opt(nums: List[int], target: int) -> List[int]:
    for i in range(len(nums)):
        for j in range(i+1, len(nums)):
            sum = nums[i] + nums[j]
            if sum == target:
                return [i, j]
    return []


# test case [3, 3], 6 -> [0, 1]
test_cases = {
    "two_sum([3, 3], 6)": {
        "args": ([3, 3], 6),
        "expected": [0, 1]
    },
    "two_sum([3, 2, 4], 6)": {
        "args": ([3, 2, 4], 6),
        "expected": [1, 2]
    }
}

for test_name, test_data in test_cases.items():
    print(f"test case: {test_name} == {test_data['expected']} ")
    assert two_sum(*test_data["args"]) == test_data["expected"]
    print("Passed")
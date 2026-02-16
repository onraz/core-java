from typing import List

def has_duplicate(nums: List[int]) -> bool:
    seen = []
    for n in nums:
        if n in seen:
            return True
        else:
            seen.append(nums)

print(has_duplicate([1,2,3]))
print(has_duplicate([1,2,3]))
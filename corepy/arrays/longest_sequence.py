
INPUT = [10, 4, 20, 2, 2, 1]

from dataclasses import dataclass

@dataclass
class Node:
    val: object
    next: Node = None

    def insert(self, val):
        n = Node(val)
        current = self
        while current.next:
            current = current.next
        current.next = n

n = Node(val=10)


print(INPUT)
print(longest_seq(INPUT))




def bserach(nums: list[int], target: int)-> int:

    def search(lo, hi):
        if lo > hi:
            return -1

        m = (lo + hi) // 2

        if target > nums[m]:
            print(f"search({m},{hi})")
            return search(m + 1, hi)
        elif target < nums[m]:
            print(f"search({lo},{m})")
            return search(lo, m - 1)
        else:
            return m

    print(nums)
    return search(0, len(nums) - 1)

print(f"{bserach([10,20,30,40,50,60,80], 60)}")
# print(f"{bserach([10,20,30,40,50,60,80], 80)}")
# print(f"{bserach([10,20,30,40,50,60,80], 10)}")
# print(f"{bserach([10,20,30,40,50,60,80], 1)}")
# print(f"{bserach([10,20,30,40,50,60,80], 81)}")


def iterative_binary_search(arr, target):
    """
    Performs iterative binary search on a sorted list.

    Args:
        arr: A sorted list of elements.
        target: The value to search for.

    Returns:
        The index of the target if found, otherwise -1.
    """
    left = 0
    right = len(arr) - 1

    while left <= right:
        # Calculate the middle index using integer division
        mid = (left + right) // 2

        # Check if the target is present at the middle
        if arr[mid] == target:
            return mid
        # If the target is greater, ignore the left half
        elif arr[mid] < target:
            left = mid + 1
        # If the target is smaller, ignore the right half
        else:
            right = mid - 1

    # Target was not found in the list
    return -1
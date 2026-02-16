# Question 2:
#
# Description
#
# A mountain array is defined as an array of integers that strictly increases until it
# reaches a peak element, and then strictly decreases.
#
# Given such an array arr, return the index of the peak element.
#  The peak element is guaranteed to exist and to be unique.
#
# Example 1:
# Input: arr = [1, 3, 5, 7, 6, 4, 2]
# Output: 3 (7)
#
# Example 2:
# Input: arr = [0, 2, 4, 6, 8, 10, 5, 3, 1]
# Output: 5 (10)
#
# Example 3:
# Input: arr = [0, 1, 0]
# Output: 1 (1)

def mountain_array(nums: list[int])-> int:
    """
    :param num: an array of integers that strictly increases until it
            reaches a peak element, and then strictly decreases.
    :return: index of the peak element.
    """
    largest_index = 0

    for i, num in enumerate(nums):
        if num > nums[largest_index]:
            largest_index = i

    return largest_index


def mountain_array2(nums: list[int])-> int:
    """
    """
    def search(l, r):
        if l == r:
            return l
        mid = (l + r) // 2
        # l  mid r
        # [0, 2, 4, 6, 8, 10, 5, 3, 1]
        # 0, 2, 4,   l = 0 r = 7 m = 7/2 = 3
        # 6,
        # 10, 5, 3, 1
        if nums[mid] <= nums[mid+1]:
            return search(mid+1, r)
        else:# nums[mid-1] >= nums[mid]:
            return search(l, mid)

    return search(0, len(nums)-1)

# test case 1
print(mountain_array2([1, 3, 5, 7, 6, 4, 2]))
print(mountain_array2([0, 2, 4, 6, 8, 10, 5, 3, 1]))
# print(mountain_array([1, 3, 5, 7, 6, 4, 2]))
# print(mountain_array([0, 2, 4, 6, 8, 10, 5, 3, 1]))
# print(mountain_array([0, 1, 0]))

assert mountain_array([1, 3, 5, 7, 6, 4, 2]) == 3
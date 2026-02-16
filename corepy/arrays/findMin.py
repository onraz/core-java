data = [1, -1, 2, 4, 2, 1]
data2 = ['a', 'b']


# Find the length of the longest subarray with the same value in each position.
# Let's apply the sliding window technique to the input array [4, 2, 2, 3, 3, 3].
inputs = [4, 2, 2, 3, 3, 1]

def same(nums):
    L = R = 0
    length = 0

    for R in range(len(nums)):
        if nums[L] != nums[R]:
            L = R

        length = max(length, R-L+1)
    return length

print(f"same: {same(inputs)}")

def closeDuplicates(nums, k):
    if not nums or k > len(nums):
        return False

    L = 0
    window = set()

    for R in range(len(nums)):
        if L - R + 1 > k:
            L += 1
            window.remove(nums[L])

        if nums[R] in window:
            return True

        window.add(nums[R])

    return False


def maxSubArray(nums):
    current = 0
    maxsum = nums[0]
    for num in nums:
        current = max(num, current + num)
        maxsum = max(current, maxsum)

    return maxsum


def maxSubArray(nums):
    L = R = 0
    maxL = maxR = 0
    current = 0
    maxsum = nums[0]

    for R in range(len(nums)):
        if current < 0:
            current = 0
            L = R # reset / fresh start

        current += nums[R]

        if current > maxsum:
            maxsum = current
            maxL, maxR = L, R

    print(f"maxsum {maxsum}")
    print(f"subarray {nums[maxL:(maxR+1)]}")

    return [maxL, maxR]


print(f"Current max {maxSubArray(data)}")
print(f"Current max {sum(data)}")
#
# min = data[0]
# for idx, num in enumerate(data):
#     if data[idx] < min:
#         min = data[idx]
#
# print(min)
#
#
# max = data[0]
# for num in data:
#     if num > max:
#         max = num
#
# print(f"max = {max}")
#
#
# mina = data[0]
# for i, num in enumerate(data):
#     if num < mina:
#         mina = num
# print(f"min = {mina}")
#
# for i, j in zip(data, data2):
#     print(f"zip {i}{j}")
#
# def kadane(nums):
#     res = 0

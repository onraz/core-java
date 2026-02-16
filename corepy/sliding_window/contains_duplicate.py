def sliding_window_pattern(arr, window_size):
    # Initialization of pointers and other necessary variables
    L = 0
    R = 0
    some_var = 0

    # Iterate over the array until the left pointer reaches the end
    while L < len(arr):
        # If window size is met or exceeded, perform operations
        if R - L + 1 == window_size:
            # Perform operations on the current window
            some_var = perform_window_operation

            # Shrink the window from the left
            L += 1
        # Move the right pointer forward
        R += 1
    # Return the result, if any
    return some_var


def containsNearbyDuplicate(nums: list[int], k: int) -> bool:
    window = set()
    L = 0
    for R in range(len(nums)):
        if R - L > k: # window must be size k or smaller
            window.remove(nums[L])
            L += 1
        if nums[R] in window:
            return True
        window.add(nums[R])
    return False

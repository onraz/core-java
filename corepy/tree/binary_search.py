arr = [10, 20, 30, 40, 30, 20, 5, 1]

def binary_search(arr, target):
    lo, hi = 0, len(arr) - 1          # inclusive bounds
    while lo <= hi:
        mid = (lo + hi) // 2          # middle index
        mid_val = arr[mid]
        if mid_val == target:
            return mid                # found
        elif mid_val < target:       # search right half
            lo = mid + 1
        else:                        # search left half
            hi = mid - 1
    return None                       # not found

def binary_search_recursive(arr, target, low, high):
    if low > high: # Base case: Target not found
        return -1
    mid = (high - low) // 2
    if arr[mid] == target:
        return mid
    elif arr[mid] > target:
        return binary_search_recursive(arr, target, low, mid - 1)
    else:
        return binary_search_recursive(arr, target, mid + 1, high)

def peakIndexInMountainArray(arr):
    left, right = 0, len(arr) - 1
    while left < right:
        mid = (left + right) // 2
        if arr[mid] < arr[mid + 1]: # If we are going up, move right
            left = mid + 1
        else: # If we are going down, peak is at mid or left of it
            right = mid
    return left  # or right (they are equal)


def peakIndexInMountainArray(arr):
    def search(left, right):
        if left == right:
            return left # Base case: one element left
        mid = (left + right) // 2
        if arr[mid] < arr[mid + 1]: # Uphill → peak to the right
            return search(mid + 1, right)
        else: # Downhill → peak at mid or left
            return search(left, mid)
    return search(0, len(arr) - 1)
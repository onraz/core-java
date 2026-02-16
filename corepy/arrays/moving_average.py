def moving_average(data, window_size):
    if len(data) < window_size:
        raise ValueError("Window size cannot be larger than the data size.")

    avg = []
    for i in range(len(data) - window_size + 1):
        window = data[i: i + window_size]
        window_average = sum(window) / window_size
        avg.append(window_average)
    return avg


def mwm(input, window):
    avg = []
    for i in range(len(input) - window):
        num = 0
        for w in range(window):
            num += input[i+w]
        avg.append(num/window)


def moving_window_mean(nums, window):
    result = []
    for i in range(len(nums) - window + 1):
        result.append(sum(nums[i:i+window]) / window)
    return result


def moving_window_mean2(nums, window):
    return [sum(nums[i:i+window]) / window for i in range(len(nums) - window + 1)]


def moving_window_mean_ON_Solution(nums, window):
    result = []
    curr_sum = sum(nums[:window])
    result.append(curr_sum / window)
    for i in range(window, len(nums)):
        curr_sum += nums[i] - nums[i - window]
        result.append(curr_sum / window)
    return result


from collections import deque

def moving_window_mean_DEQ_ON(nums, window):
    dq = deque()
    result = []
    curr_sum = 0

    for i, num in enumerate(nums):
        dq.append(num)
        curr_sum += num

        # If window is overfull → pop leftmost (oldest)
        if len(dq) > window:
            curr_sum -= dq.popleft()

        # Record mean once window is full
        if len(dq) == window:
            result.append(curr_sum / window)

    return result


from collections import deque

def sliding_window_max(nums, window):
    dq = deque()   # stores indices
    result = []

    for i, num in enumerate(nums):
        # 1️⃣ Remove smaller elements (not useful for max)
        while dq and nums[dq[-1]] < num:
            dq.pop()

        # 2️⃣ Add current index
        dq.append(i)

        # 3️⃣ Remove elements out of this window
        if dq[0] <= i - window:
            dq.popleft()

        # 4️⃣ Record the max (front of deque) once window is full
        if i >= window - 1:
            result.append(nums[dq[0]])

    return result

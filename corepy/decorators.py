import time
import functools


def log(level):
    print(f"logging {level}")

    def timer_dec(base_func):
        @functools.wraps(base_func)
        def wrapper(*args, **kwargs):
            start_time = time.time()
            print(f"[{level}] Starting {base_func.__name__}")
            result = base_func(*args, **kwargs)
            end_time = time.time()
            print(f"[{level}] Finished {base_func.__name__} in {round((end_time - start_time) * 1000, 2)}ms")
            return result

        return wrapper

    return timer_dec


def greeting(func):
    print(f"Greeting decorator!")

    @functools.wraps(func)
    def wrapper(*args, **kwargs):
        print(f"Greeting: {func.__name__}")
        result = func(*args, **kwargs)
        return result

    return wrapper


@greeting
@log(level="DEBUG")
def brew_tea(type="green"):
    print(f"Brewing Tea: {type}")


brew_tea()

status_code = 404

match status_code:
    case 200:
        print("OK - Success")
    case 404:
        print("Not Found - Resource missing")
    case 500 | 404 if status_code == 404:
        print("Internal Server Error")
    case _:
        print("Unknown status code")

nums = [48, 2, 3, 7, 10, 20, 1, 4, 6]

import heapq

heap = []
print("smallest three using max heap")
for num in nums:
    if len(heap) < 3:
        heapq.heappush(heap, -num)
    elif num < -heap[0]:
        heapq.heapreplace(heap, -num)

print(heap)

print("largest three using min heap")
for num in nums:
    if len(heap) < 3:
        heapq.heappush(heap, num)
    elif num > heap[0]:
        heapq.heapreplace(heap, num)

print(heap)

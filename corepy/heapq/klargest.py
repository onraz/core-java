import random
from collections import Counter

arr = [ random.randint(1, 50)  for i in range(1, 11) ]

print(sorted(arr))
print(arr)

import heapq

min_heap = []
for n in arr:
    heapq.heappush(min_heap, n)
    if len(min_heap) > 3:
        heapq.heappop(min_heap)

min_heap = []
for n in arr:
    if len(min_heap) < 3:
        heapq.heappush(min_heap, n)
    elif n > min_heap[0]:
        heapq.heapreplace(min_heap, n)


print(f"min_heap 1: {min_heap}")
print(f"min_heap 2: {min_heap}")

arr = [random.randint(1, 10) for _ in range(50)]
cnt = Counter(arr)
print(cnt)
min_heap = []
for num, freq in cnt.items():
    heapq.heappush(min_heap, (-freq, num))
    if len(min_heap) > 3:
        heapq.heappop(min_heap)

ans = {v for k,v in min_heap}
print(type(ans))
print(ans)


def kth_largest(nums, k):
    heap = []

    for n in nums:
        heapq.heappush(heap, n)
        if len(heap) > k:
            heapq.heappop(heap)

    return heap[0]
arr = [5, 6, 7, 8, 9]
arr = [i for i in range(90, 100)]




def lcp(strs: list[str]):
    prefix = ""
    for i in range(len(strs[0])):
        for s in strs:
            if i == len(s) or s[i] != strs[0][i]:
                return prefix
        prefix += strs[0][i]
    return prefix


def lcp(strs: list[str])-> str:
    prefix = ""
    for i in range(len(strs[0])):
        for s in strs:
            if i == len(s) or s[i] != strs[0][i]:
                return prefix
        prefix += strs[0][i]
    return prefix

print(lcp(['mika', 'mikala', 'mij']))

for val in arr:
    print(val)

for idx, val in enumerate(arr):
    print(f"{idx}==={val}")
    arr.remove(val)
print(arr)

def twosum(n: list[int], target: int)-> tuple[int,int]:
    map: dict = {}
    for idx, num in enumerate(n):
        other = target - num
        if other in map:
            other_idx = map[other]
            return other_idx, idx
        map[num] = idx
    print(map)
    return -1, -1

print(twosum([5, 6, 7, 8, 9], 19))

def find_duplicate(nums: list[int])->bool:
    seen = set()
    for n in nums:
        if n in seen:
            return True
        seen.add(n)
    return False

print(find_duplicate([1,2,3]))
print(find_duplicate([1,2,2]))



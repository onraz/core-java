

s = ['a', 'b', 'c']
t = ['x', 'y']

def printer(s, t):
    def dfs(start, path, indent=0):
        if start == len(s):
            return

        for i in range(start+1, len(t)):
            margin = " " * indent
            # print(f"{margin} - path: {path}")
            if False: #(s[start], t[i]) in path or i == start:
                continue
            else:
                path.append((s[start], t[i]))
                print(f"{margin} - {(s[start], t[i])}")
                dfs(i+1, path, indent + 2)
    dfs(0, [])


printer(s, s)

# def printer2(s, t):
#     def dfs(k, path, indent=0):
#         if not k:
#             return
#         item = k[0]
#         for j in range(len(t)):
#             new_s = k.copy()
#             margin = " " * indent
#             # print(f"{margin} - path: {path}")
#             if (item, t[j]) in path:
#                 continue
#             else:
#                 path.append((item, t[j]))
#                 print(f"{margin} - {(item, t[j])}")
#                 new_s.remove(item)
#                 dfs(new_s, path, indent + 2)
#     dfs(s, [])

# printer2(s, s)
# s = ['a', 'b', 'c']
# def subset(s):
#     if not s:
#         return [[]]
#     item = s[0]
#     rest = subset(s[1:])
#     return rest + [[item] + s for s in rest]
#
# def permute(s):
#     res = []
#     def perm(i, path):
#         if len(path) == len(s):
#             res.append(path.copy())
#             return
#         item = s[i]
#         path.append(item)
#         print(path)
#         for k in range(i + 1, len(s)):
#             path.append(s[k])
#             perm(i+1, path)
#
#     for a in range(len(s)):
#         perm(a, [])
#     return res
#
# print(permute(s))
# # res = sorted(printer(s, t))
# # for a in res:
# #     print(a)
#
#
# def permute_dfs(nums):
#     results = []
#
#     def backtrack(current_permutation, used_indices):
#         # Base case: A complete permutation is formed
#         if len(current_permutation) == len(nums):
#             results.append(list(current_permutation))  # Add a copy
#             return
#
#         # Recursive step: Explore choices
#         for i in range(len(nums)):
#             if i not in used_indices:  # Check if element is already used
#                 # Choose
#                 current_permutation.append(nums[i])
#                 used_indices.add(i)
#
#                 # Explore
#                 backtrack(current_permutation, used_indices)
#
#                 # Unchoose (Backtrack)
#                 used_indices.remove(i)
#                 current_permutation.pop()
#
#     backtrack([], set())  # Start with an empty permutation and no used elements
#     return results
#
#
# # Example usage:
# my_list = [1, 2, 3]
# all_permutations = permute_dfs(my_list)
# print(all_permutations)
#
#
#
# class Solution:
#     def subsets(self, nums: list[int]) -> list[list[int]]:
#         result = []
#         current_subset = []
#
#         def dfs(index):
#             # Base case: All elements considered
#             if index == len(nums):
#                 result.append(list(current_subset))  # Add a copy of the current subset
#                 return
#
#             # Include the current element
#             current_subset.append(nums[index])
#             dfs(index + 1)
#
#             # Exclude the current element (backtrack)
#             current_subset.pop()
#             dfs(index + 1)
#
#         dfs(0)  # Start DFS from the beginning of the list
#         return result
#
#
#
#
#
#
# def dfs_order(nums):
#     res = []
#     used = set()
#     def dfs(current):
#         if len(current) == len(nums):
#             res.append(current.copy())
#             return
#
#         for j in range(len(nums)):
#             if nums[j] in current:
#                 continue
#             current.append(nums[j])
#             used.add(nums[j])
#
#             dfs(current)
#
#             current.pop()
#             used.remove(nums[j])
#
#     dfs([])
#     return res
#
# print(dfs_order(['a', 'b', 'c']))


class Book:
    def __init__(self, title, author):
        self.title = title
        self.author = author

    def __str__(self):
        return f"{self.title} by {self.author}"

    def __eq__(self, other):
        if isinstance(other, Book):
            return self.title == other.title and self.author == other.author
        return False

book1 = Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams")
book2 = Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams")
book3 = Book("Pride and Prejudice", "Jane Austen")

print(book1)  # Invokes __str__
print(book1 == book2) # Invokes __eq__
print(book1 == book3) # Invokes __eq__
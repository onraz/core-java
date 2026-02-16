graph = {
    'A': ['B', 'C'],
    'B': ['A', 'D', 'E'],
    'C': ['A', 'F'],
    'D': ['B'],
    'E': ['B', 'F'],
    'F': ['C', 'E']
}

graph2 = {
    'A': ['B'],
    'B': ['F'],
    'C': ['B', 'D'],
    'D': [],
    'E': [],
    'F': []
}


def dfs_start():
    visited = set()

    def dfs(graph, current):
        if current in visited:
            return

        print(current)
        visited.add(current)

        for child in graph[current]:
            dfs(graph, child)

    dfs(graph, 'A')


# dfs_start()


def count_component(graph_nodes):
    visited = set()

    def dfs(graph, current):
        if current in visited:
            return
        print(current)
        visited.add(current)
        for child in graph[current]:
            if child not in visited:
                dfs(graph, child)

    count = 0
    for n in graph_nodes:
        if n not in visited:
            dfs(graph_nodes, n)
            count += 1

    return count


# print(count_component(graph2))
def find_path_dfs(graph, start, target):
    visited = set()
    path = []

    def dfs(node):
        visited.add(node)
        path.append(node)

        if node == target:
            return True  # path complete

        for nbr in graph.get(node, []):
            if nbr not in visited:
                if dfs(nbr):       # if sub-DFS found target
                    return True

        path.pop()  # backtrack
        return False

    if dfs(start):
        return path
    else:
        return None

def all_paths_dfs(graph, start, target):
    res = []
    path = []

    def dfs(node):
        path.append(node)
        if node == target:
            res.append(path.copy())
        else:
            for nbr in graph.get(node, []):
                if nbr not in path:   # using path as visited here
                    dfs(nbr)
        path.pop()

    dfs(start)
    return res

graph = {'A':['B','C'], 'B':['D'], 'C':['D'], 'D':[]}
# print(all_paths_dfs(graph, 'A', 'D'))

def all_path_dfs_iter(graph, start, target):
    stack = [(start, [start])]   # stack holds (node, path)
    res = []
    while stack:
        node, path = stack.pop()
        if node == target:
            res.append(path)
            continue

        for nbr in graph.get(node, []):
            if nbr not in path:
                stack.append((nbr, path + [nbr]))

    return res

# print(find_path_dfs_iter(graph, 'A', 'D'))



def find_path_dfs_iter(graph, start, target):
    stack = [(start, [start])]   # stack holds (node, path)
    visited = set()

    while stack:
        node, path = stack.pop()
        if node == target:
            return path

        if node in visited:
            continue
        visited.add(node)

        for nbr in graph.get(node, []):
            if nbr not in visited:
                stack.append((nbr, path + [nbr]))

    return None



def find_path(graph, start, target):
    visited = set([start])
    parent = {start: None}
    stack = [start]

    while stack:
        node = stack.pop()
        if node == target:
            # reconstruct
            path = []
            cur = target
            while cur is not None:
                path.append(cur)
                cur = parent[cur]
            return path[::-1]

        visited.add(node)

        for nbr in graph[node]:
            if nbr not in visited:
                parent[nbr] = node
                stack.append(nbr)
    return None

print(find_path(graph, 'A', 'D'))

from collections import defaultdict


def dfs_all_parents(graph, start):
    stack = [start]
    visited = set()
    parents = defaultdict(list)

    while stack:
        node = stack.pop()
        if node not in visited:
            visited.add(node)
            for nbr in graph.get(node, []):
                parents[nbr].append(node)
                stack.append(nbr)
    return parents

def backtrack_all_paths(parents, start, target):
    all_paths = []

    def dfs(node, path):
        if node == start:
            all_paths.append(path[::-1])  # reverse since we’re backtracking
            return
        for p in parents[node]:
            dfs(p, path + [p])

    if target not in parents and target != start:
        return []

    dfs(target, [target])
    return all_paths


def backtrack_all_paths_iter(parents, start, target):
    all_paths = []
    stack = [(target, [target])]

    while stack:
        node, path = stack.pop()

        if node == start:
            all_paths.append(path[::-1])
            continue

        for p in parents.get(node, []):
            stack.append((p, path + [p]))

    return all_paths


def all_paths_dfs(graph, start, target):
    parents = build_parents(graph, start)
    return backtrack_all_paths(parents, start, target)

def depth_sum(nested_list) -> int:
    def dfs(depth, nested):
        total = 0
        for num in nested:
            if isinstance(nested, int):
                total += num * depth
            else:
                total += dfs(depth + 1, nested)
        return total

    return depth_sum(1, nested_list)

def letterCombinations(digits: str) -> list[str]:
    res = []
    digitToChar = {
        "2": "abc",
        "3": "def",
        "4": "ghi",
        "5": "jkl",
        "6": "mno",
        "7": "qprs",
        "8": "tuv",
        "9": "wxyz",
    }

    def backtrack(i, curStr):
        if len(curStr) == len(digits):
            res.append(curStr)
            return
        for c in digitToChar[digits[i]]:
            backtrack(i + 1, curStr + c)

    if digits:
        backtrack(0, "")

    return res
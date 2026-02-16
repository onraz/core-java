from collections import deque
from tkinter.ttk import Treeview
from typing import Optional


class TreeNode:
    def __init__(self, data):
        self.value = data
        self.left = None
        self.right = None

def bfs(root):
    if root is None:
        return
    queue = deque([root, 1]) # for BFS deque is needed
    depth = 0
    while queue:
        node, level = queue.popleft()
        if node.left:
            queue.append([node.left, level + 1])
        if node.right:
            queue.append([node.right, level + 1])

        depth = max(depth, level)

def bfs(root):
    if root is None:
        return
    queue = deque([root]) # for BFS deque is needed
    while queue:
        node = queue.popleft()
        print(node.value, end=" ")
        if node.left:
            queue.append(node.left)
        if node.right:
            queue.append(node.right)


def dfs_preorder(root):
    if root:
        print(root.value, end=" ")
        dfs_preorder(root.left)
        dfs_preorder(root.right)

def dfs_inorder(root):
    if root:
        dfs_inorder(root.left)
        print(root.value, end=" ")
        dfs_inorder(root.right)

def dfs_postorder(root):
    if root:
        dfs_postorder(root.left)
        dfs_postorder(root.right)
        print(root.value, end=" ")

root = TreeNode(1)
root.left = TreeNode(2)
root.right = TreeNode(3)
root.left.left = TreeNode(4)
root.left.right = TreeNode(5)

print("BFS:")
bfs(root)  # Output: 1 2 3 4 5


print("\nDFS Preorder:")
dfs_preorder(root) # Output: 1 2 4 5 3
print("\nDFS Inorder:")
dfs_inorder(root) # Output: 4 2 5 1 3
print("\nDFS Postorder:")
dfs_postorder(root) # Output: 4 5 2 3 1


def searchBST(root: TreeNode | None, target: int) -> TreeNode | None:
    if not root:
        return
    if target > root.val:
        return searchBST(root.right, target)
    elif target < root.val:
        return searchBST(root.left, target)
    else:
        return root


from collections import deque

def max_depth_bfs(root: TreeNode | None) -> int:
    if not root:
        return 0

    queue = deque([root])
    depth = 0
    while queue:
        level_size = len(queue)          # nodes on current level
        for _ in range(level_size):
            node = queue.popleft()
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
        depth += 1                       # finished one level
    return depth

print(max_depth_bfs(root))   # → 3

def depth(root: TreeNode):
    if not root:
        return 0
    return 1 + max(depth(root.left), depth(root.right))

def level(root: TreeNode):
    if not root:
        return 0
    stack = [(root, 1)]
    level = 0
    while stack:
        curr, depth = stack.pop()
        level = max(level, depth)
        if curr.left:
            stack.append((curr.left, depth + 1))
        if curr.right:
            stack.append((curr.right, depth + 1))
    return level

def invertTree(root: Optional[TreeNode]) -> Optional[TreeNode]:
    if not root:
        return None

    temp = root.left
    root.left = invertTree(root.right)
    root.right = invertTree(temp)

    return root

def isSameTree(self, p: Optional[TreeNode], q: Optional[TreeNode]) -> bool:
    if not p and not q:
        return True
    if not p or not q:
        return False

    return p.val == q.val and self.isSameTree(p.left, q.left) and self.isSameTree(p.right, q.right)

def isSubtree(self, root: Optional[TreeNode], subRoot: Optional[TreeNode]) -> bool:
    if not subRoot:
        return True
    if not root:
        return False

    if self.sameTree(root, subRoot):
        return True
    else:
        return (self.isSubtree(root.left, subRoot) or self.isSubtree(root.right, subRoot))

def sameTree(self, p, q):
    if not p and not q:
        return True
    if not p or not q:
        return False
    return p.val == q.val and self.sameTree(p.left, q.left) and self.sameTree(p.right, q.right)

print("\nmax depth")
print(max_depth_bfs(root))
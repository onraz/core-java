print("--------------------")


class Node:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


rootNode = Node(5)
# Left subtree
rootNode.left = Node(3)
rootNode.left.left = Node(2)
rootNode.left.right = Node(4)

# Right subtree
rootNode.right = Node(8)
rootNode.right.right = Node(9)
rootNode.right.left = Node(7)

def printTree(root: Node):
    levels = []
    def level_order(node, level):
        if node:
            if len(levels) == level:
                levels.append([])
            levels[level].append(node.val)
            level_order(node.left, level + 1)
            level_order(node.right, level + 1)

    level_order(root, 0)
    return levels


def height(root: Node) -> int:
    if root is None:
        return 0
    else:
        return max(height(root.left), height(root.right)) + 1

# print(printTree(rootNode))
print(height(rootNode))

def preOrder(root):
    if root:
        print(root.val)
        preOrder(root.left)
        preOrder(root.right)

preOrder(rootNode)


def levelOrder(root):
    levels = []
    def helper(node, level):
        if node:
            if len(levels) == level:
                levels.append([])
            levels[level].append(node.val)
            helper(node.left, level + 1)
            helper(node.right, level + 1)
    
    helper(root, 0)
    return levels

print(levelOrder(rootNode))

def insert(root: Node, val: int) -> Node:
    if not root:
        return Node(val)
    
    if val > root.val:
        root.right = insert(root.right, val)
    else:
        root.left = insert(root.left, val)

    return root

insert(rootNode, 12)
print(levelOrder(rootNode))

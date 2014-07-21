package algorithms.tree;

public class BinarySearchTreeAlgorithms {

	public static <T extends Comparable<T>> boolean contains(BinaryTree<T> tree, T value) {
		if (tree == null) return false;
		int compareResult = value.compareTo(tree.data);
		
		if (compareResult == 0) {
			return true;
		} else if (compareResult > 0) {
			return contains(tree.right, value);
		} else {
			return contains(tree.left, value);
		}
	}
	
	public static <T extends Comparable<T>> BinaryTree<T> findMax(BinaryTree<T> tree) {
		while (tree.right != null) {
			tree = tree.right;
		}
		return tree;
	}
	
	public static <T extends Comparable<T>> BinaryTree<T> findMin(BinaryTree<T> tree) {
		while (tree.left != null) {
			tree = tree.left;
		}
		return tree;
	}
	
	public static <T extends Comparable<T>> boolean isBstTree(BinaryTree<T> tree) {
		T max = findMax(tree).data;
		T min = findMin(tree).data;
		return isBstTree(tree, min, max);
	}
	
	public static <T extends Comparable<T>> boolean isBstTree(BinaryTree<T> tree, T left, T right) {
		if (tree == null) return true;
		System.out.println("Comparing " + tree.data + " to left " + left + " right " + right);
		if (tree.data.compareTo(left) >= 0 && tree.data.compareTo(right) <= 0) {
			return isBstTree(tree.left, left, tree.data) &&
					isBstTree(tree.right, tree.data, right);
		}
		return false;
	}
	
	public static <T extends Comparable<T>> BinaryTree<T> insert(BinaryTree<T> tree, T value) {
		if (tree == null) return new BinaryTree<T>(value);
		int compareResult = value.compareTo(tree.data);
		if (compareResult > 0 ) {
			tree.right = insert(tree.right, value);
		} else if (compareResult < 0) {
			tree.left = insert(tree.left, value);
		} else {
			// duplicate do nothing
		}
		return tree;
	}
	
	public static <T extends Comparable<T>> BinaryTree<T> remove(BinaryTree<T> tree, T value) {
		if (tree == null) return tree; // throw exception
		int result = value.compareTo(tree.data);
		if (result > 0) {
			// keep searching right
			tree.right = remove(tree.right, value);
		} else if (result < 0) {
			// keep searching left
			tree.left = remove(tree.left, value);
		} else if (tree.left != null && tree.right != null) {
			// 1. found node has two children, replace it with min of right tree
			tree.data = findMin(tree.right).data;
			// Now just remove the duplicate value from the right, as it is 
			// guaranteed to have one or no child.
			tree.right = remove(tree.right, tree.data);
		} else {
			// 2. found node has only one child, replace it with its child
			// 3. found node has no child, replace it with null (tree.right)
			tree = (tree.left != null) ? tree.left : tree.right;
		}
		return tree;
	}
	

	public static <T extends Comparable<T>> BinaryTree<T> flatten(BinaryTree<T> tree) {
		/*
		 * 
     root                        root
     /  \            ->            \
  left  right                      left
  /									 \
left                                  left
 										\
                                    	right
		 */
		if (tree == null) return tree;
		// push the right tree in the stack
		BinaryTree<T> right = tree.right;
		// keep chaining the left until we find a left that is a leaf
		if (tree.left != null) {
			tree.right = tree.left;
			tree.left = null;
			tree = flatten(tree.right);
		}
		// now tree is a left leaf, put the right from stack as its right
		if (right != null) {
			tree.right = right;
			tree = flatten(tree.right);
		}
		return tree;
	}

	/**
	 * Lowest Common Ancestor of (p,q)
	 * O(n), Since this is bottom up approach and each node is touched only once
	 */
	public static <T extends Comparable<T>> BinaryTree<T> lowestCommonAncestor(BinaryTree<T> root, T p, T q) {
        if (root == null) return null;

        // if we find p or q pass it up to the parent in line 2 or 3
        if (root.data == p || root.data == q) return root;

        // 2. find the bottom most node that contains p or q in the left
        BinaryTree<T> left = lowestCommonAncestor(root.left, p, q);
        // 3. find the bottom most node that contains p or q in the right
        BinaryTree<T> right = lowestCommonAncestor(root.right, p, q);

        // 4. found p and q on both sides, so root is ancestor
        if (left != null && right != null) return root;

        // 5. both p and q are either on left or right
        return (left != null) ? left : right;
    }
	
	
}

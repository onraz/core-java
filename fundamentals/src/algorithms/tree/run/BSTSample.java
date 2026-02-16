package algorithms.tree.run;

import static algorithms.tree.BinarySearchTreeAlgorithms.flatten;
import static algorithms.tree.BinarySearchTreeAlgorithms.insert;
import static algorithms.tree.BinarySearchTreeAlgorithms.isBstTree;
import static algorithms.tree.BinarySearchTreeAlgorithms.lowestCommonAncestor;
import algorithms.tree.BinaryTree;

public class BSTSample {
	
	// flatten tree
	/*
	 * right in stack
	 * 
	 * if (left) 
	 * 
	 * left = flatten left
	 * root.right = left
	 * 
	 * left.right = 
	 * flatten right
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(20);
		tree.setLeft(10);
		tree.setRight(30).setLeft(25);

		System.out.println("Is binary tree: " + isBstTree(tree));
		
		System.out.println("Lca(10,30) is root: " + (lowestCommonAncestor(tree, 10, 30) == tree));
		
		
		BinaryTree<Integer> bst = new BinaryTree<Integer>(20);
		insert(bst, 30);
		insert(bst, 10);
		insert(bst, 25);
		
		System.out.println("Is binary tree: " + isBstTree(bst));
		flatten(bst);
	}
	
}

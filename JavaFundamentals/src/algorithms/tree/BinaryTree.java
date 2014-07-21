package algorithms.tree;

public class BinaryTree<T extends Comparable<T>> {
	public T data;
	BinaryTree<T> left;
	BinaryTree<T> right;
	
	public BinaryTree(T value) {
		this.data = value;
	}
	
	public BinaryTree<T> setLeft(T value) {
		BinaryTree<T> leftTree = new BinaryTree<>(value);
		this.left = leftTree;
		return leftTree;
	}
	
	public BinaryTree<T> setRight(T value) {
		BinaryTree<T> rightTree = new BinaryTree<>(value);
		this.right = rightTree;
		return rightTree;
	}
	
}

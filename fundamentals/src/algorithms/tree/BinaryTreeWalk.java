package algorithms.tree;

import java.util.function.Consumer;

public interface BinaryTreeWalk {
	
	public static <T extends Comparable<T>> void walkPreorder(BinaryTree<T> tree, Consumer<T> consumer) {
		consumer.accept(tree.data);
		if (tree.left != null) walkPreorder(tree.left, consumer);
		if (tree.right != null) walkPreorder(tree.right, consumer);
	}
	
	public static <T extends Comparable<T>> void walkInorder(BinaryTree<T> tree, Consumer<T> consumer) {
		System.out.print("(");
		if (tree.left != null) walkInorder(tree.left, consumer);
		consumer.accept(tree.data);
		if (tree.right != null) walkInorder(tree.right, consumer);
		System.out.print(")");
	}
	
	public static <T extends Comparable<T>> void walkPostorder(BinaryTree<T> tree, Consumer<T> consumer) {
		if (tree.left != null) walkPostorder(tree.left, consumer);
		if (tree.right != null) walkPostorder(tree.right, consumer);
		consumer.accept(tree.data);
	}
}

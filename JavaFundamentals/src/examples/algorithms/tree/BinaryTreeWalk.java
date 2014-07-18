package examples.algorithms.tree;

import java.util.function.Consumer;

public class BinaryTreeWalk {
	enum WalkOrder { PreOrder, InOrder, PostOrder }
//	private WalkOrder order = WalkOrder.PreOrder;
	
	public static <T> void walkPreorder(BinaryTree<T> tree, Consumer<T> consumer) {
		consumer.accept(tree.element);
		if (tree.left != null) walkPreorder(tree.left, consumer);
		if (tree.right != null) walkPreorder(tree.right, consumer);
	}
	
	public static <T> void walkInorder(BinaryTree<T> tree, Consumer<T> consumer) {
		System.out.print("(");
		if (tree.left != null) walkInorder(tree.left, consumer);
		consumer.accept(tree.element);
		if (tree.right != null) walkInorder(tree.right, consumer);
		System.out.print(")");
	}
	
	public static <T> void walkPostorder(BinaryTree<T> tree, Consumer<T> consumer) {
		if (tree.left != null) walkPostorder(tree.left, consumer);
		if (tree.right != null) walkPostorder(tree.right, consumer);
		consumer.accept(tree.element);
	}
}

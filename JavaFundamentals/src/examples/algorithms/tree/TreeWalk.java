package examples.algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class TreeWalk {
	
	public static <T> void walkDfs(Tree<T> tree, Consumer<T> consumer) {
		consumer.accept(tree.head);
		for (Tree<T> subTree : tree.leafs) {
			walkDfs(subTree, consumer);
		}
	}
	
	public static <T> void walkBfs(Tree<T> tree, Consumer<T> consumer) {
		Queue<T> nodes = new LinkedList<T>();
		consumer.accept(tree.head);
		
		for (Tree<T> subTree : tree.leafs) {
			walkDfs(subTree, consumer);
		}
	}	
}

package algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class TreeWalk {
	
	public static <T> void walkDfs(Tree<T> tree, Consumer<T> consumer) {
		// consume parent
		consumer.accept(tree.head);
		// consume leafs
		for (Tree<T> leaf : tree.leafs) {
			walkDfs(leaf, consumer);
		}
	}
	
	public static <T> void walkBfs(Tree<T> tree, Consumer<T> consumer) {
		// Initialise queue with root
		Queue<Tree<T>> queue = new LinkedList<>();
		queue.add(tree);
		
		// consume parent and enqueue children
		while (!queue.isEmpty()) {
			Tree<T> node = queue.remove();
			// consume parent
			consumer.accept(node.head);
			// queue children
			for (Tree<T> leaf : node.leafs) {
				queue.add(leaf);
			}
		}
	}	
}

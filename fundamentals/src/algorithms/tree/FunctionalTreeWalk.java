package algorithms.tree;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalTreeWalk<T> {
	
	private Tree<T> tree;
	
	private Predicate<T> selector = e -> true;
	private Function<T,T> mapper = e -> e;
	
	public FunctionalTreeWalk(Tree<T> tree) {
		this.tree = tree;
	}
	
	public FunctionalTreeWalk<T> filter(Predicate<T> selector) {
		this.selector = selector;
		return this;
	}
	
	public FunctionalTreeWalk<T> map(Function<T,T> mapper) {
		this.mapper = mapper;
		return this;
	}
	
	public void forEach(Consumer<T> consumer) {
		this.walk(tree, consumer);
	}
	
	private void walk(Tree<T> tree, Consumer<T> consumer) {
		if (selector.test(tree.head)) {
			T value = mapper.apply(tree.head);
			consumer.accept(value);
		}
		for (Tree<T> childTree : tree.leafs) {
			walk(childTree, consumer);
		}		
	}
	
}

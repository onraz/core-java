package examples.algorithms.tree;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SampleTreeWalk<T> {
	enum WalkOrder { PreOrder, InOrder, PostOrder }
	
	private Tree<T> tree;
	private WalkOrder order = WalkOrder.PreOrder;
	
	private Predicate<T> selector = e -> true;
	private Function<T,T> mapper = e -> e;
	
	public SampleTreeWalk(Tree<T> tree, WalkOrder order) {
		this.tree = tree;
		this.order = order;
	}
	
	public SampleTreeWalk<T> filter(Predicate<T> selector) {
		this.selector = selector;
		return this;
	}
	
	public SampleTreeWalk<T> map(Function<T,T> mapper) {
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

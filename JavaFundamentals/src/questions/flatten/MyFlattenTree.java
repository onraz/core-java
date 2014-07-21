package questions.flatten;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFlattenTree<T> implements FlattenTree<T> {

    // Extract a single element
    private final Function<T, T> flattenSingle = new Function<T, T>() {
		@Override
		public T apply(T p) {
			return p;
		}
	};
	
	// Traverse and flatten a triple, flatten the left, middle and right subtrees
	private final Function<Triple<Tree<T>>, List<T>> flattenTriple = new Function<Triple<Tree<T>>, List<T>>() {
		@Override
		public List<T> apply(Triple<Tree<T>> subTree) {
			List<T> values = new ArrayList<T>();
			values.addAll(flattenInOrder(subTree.left()));
			values.addAll(flattenInOrder(subTree.middle()));
			values.addAll(flattenInOrder(subTree.right()));
			return values;
		}
	};

	/**
	 * InOrder Traverse and flatten a tree as a list
	 */
	public List<T> flattenInOrder(Tree<T> tree) {
		if (tree == null) {
			throw new IllegalArgumentException("Tree can't be null");
		}
        
		if (tree.get().isLeft()) {
            // apply the left function on the single element
			return Arrays.asList(tree.get().ifLeft(flattenSingle));
		} else {
            // apply the right function on triple
			return tree.get().ifRight(flattenTriple);
		}
	}

}

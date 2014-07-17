package examples.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
	T head;
	List<Tree<T>> leafs;
	
	public Tree(T head) {
		this.head = head;
		this.leafs = new ArrayList<>();
	}
	
	public Tree<T> addLeaf(T leaf) {
		Tree<T> leafTree = new Tree<>(leaf);
		this.leafs.add(leafTree);
		return leafTree;
	}
	
	
}
/*
 * http://www.java2s.com/Code/Java/Collections-Data-Structure/Yourowntreewithgenericuserobject.htm
 */
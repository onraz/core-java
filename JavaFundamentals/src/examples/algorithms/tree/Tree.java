package examples.algorithms.tree;

import java.util.List;

public class Tree<T> {
	T head;
	List<Tree<T>> leafs;
	
	
	public Tree(T head) {
		this.head = head;
	}
}
//http://www.java2s.com/Code/Java/Collections-Data-Structure/Yourowntreewithgenericuserobject.htm
package examples.algorithms.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import examples.algorithms.tree.SampleTreeWalk.WalkOrder;

public class SampleWalk {
	
	static Tree<String> familyTree;
	static {
		familyTree = new Tree<>("family");
		familyTree.addLeaf("1.1").addLeaf("1.1.1");
		familyTree.addLeaf("1.2").addLeaf("1.2.1").addLeaf("1.2.1.1");
		Tree<String> thirdTree = familyTree.addLeaf("1.3");
		thirdTree.addLeaf("1.3.1").addLeaf("1.3.1.1");
		thirdTree.addLeaf("1.3.2");
	}
	
	static void walkFunctional() {
		// Using a functional api
		new SampleTreeWalk<>(familyTree, WalkOrder.InOrder)
		.filter(e -> !e.equals("family"))
		.map(e -> e.toUpperCase())
		.forEach(System.out::println);		
		
		// Simpler api - using consumer
		Map<Integer, String> map = new HashMap<>();
		TreeWalk.walk(familyTree, e -> map.put(e.length(), e));
		
		System.out.println("Result: " + map);		
	}
	
	public static void main(String[] args) {
		// Simpler api - using consumer
		List<String> nodes = new ArrayList<>();
		TreeWalk.walk(familyTree, nodes::add);
		
		System.out.println("Result: " + nodes);
	}
}

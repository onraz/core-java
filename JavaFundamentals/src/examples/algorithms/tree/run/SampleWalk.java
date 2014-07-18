package examples.algorithms.tree.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import examples.algorithms.tree.FunctionalTreeWalk;
import examples.algorithms.tree.Tree;
import examples.algorithms.tree.TreeWalk;

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
		new FunctionalTreeWalk<>(familyTree)
		.filter(e -> !e.equals("family"))
		.map(e -> e.toUpperCase())
		.forEach(System.out::println);		
		
		// Simpler api - using consumer
		List<String> nodes = new ArrayList<>();
		TreeWalk.walkDfs(familyTree, nodes::add);
		
		System.out.println("Dfs Result: " + nodes);
		
		// Simpler api - using consumer
		Map<Integer, String> map = new HashMap<>();
		TreeWalk.walkDfs(familyTree, e -> map.put(e.length(), e));
		
		System.out.println("Result: " + map);		
	}
	
	public static void main(String[] args) {
		System.out.println("DFS");
		TreeWalk.walkDfs(familyTree, System.out::println);
		
		System.out.println("BFS");
		TreeWalk.walkBfs(familyTree, System.out::println);
	}
}

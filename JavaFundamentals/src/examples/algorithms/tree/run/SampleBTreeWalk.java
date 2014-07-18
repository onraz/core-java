package examples.algorithms.tree.run;

import examples.algorithms.tree.BinaryTree;
import examples.algorithms.tree.BinaryTreeWalk;

public class SampleBTreeWalk {
	public static void main(String[] args) {
		BinaryTree<String> operation = new BinaryTree<>("+");
		
		BinaryTree<String> left = operation.setLeft("*");
		left.setLeft("3");
		left.setRight("5");
		
		BinaryTree<String> right = operation.setRight("/");
		right.setLeft("10");
		right.setRight("2");

		System.out.println("\nInorder: ");
		BinaryTreeWalk.walkInorder(operation, System.out::print);
		System.out.println("\nPreorder: ");
		BinaryTreeWalk.walkPreorder(operation, System.out::print);
		System.out.println("\nPostorder: ");
		BinaryTreeWalk.walkPostorder(operation, System.out::print);
		
		
		operation = new BinaryTree<>("A");
		
		left = operation.setLeft("A.L");
		left.setLeft("A.L.L");
		left.setRight("A.L.R");
		
		right = operation.setRight("A.R");
		right.setLeft("A.R.L");
		right.setRight("A.R.R");
		
		System.out.println("\n\nInorder: ");
		BinaryTreeWalk.walkInorder(operation, System.out::println);
		System.out.println("\nPreorder: ");
		BinaryTreeWalk.walkPreorder(operation, System.out::println);
		System.out.println("\nPostorder: ");
		BinaryTreeWalk.walkPostorder(operation, System.out::println);
	}
}

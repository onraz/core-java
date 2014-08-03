package core.collection;

import java.util.Arrays;

public class Iterators {
	public static void main(String[] args) {
		Iterable<String> myIterable = Arrays.asList("Raz", "Dina", "Rahil", "My Jaan");
		
		// Approach 1 : Iterable.forEach
		myIterable.forEach(System.out::println);
		
		// Approach 2: Iterable for Each Loop
		for (String name : myIterable) {
			System.out.println(name);
		}
	}
}

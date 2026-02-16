package functional.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Coding is communication using the expressiveness of language
 * Functional Programming promotes:
 * 	1. Declarative Programming (Say what, not how - so compiler can optimise)
 * 	2. Immutability (allows thread safety)
 * 	3. Use function / lambda / closures as first class objects
 * 
 * Code should be read like a story, not like a puzzle
 * 
 * @author Raz
 *
 */
public interface FunctionalDeclarativeProgramming {

	static void main(String[] args) {
		List<String> students = Arrays.asList("Ray", "Bob", "Rob", "Bill");
		
		/*
		 * Display names in uppercase that start with B
		 * using imperative style
		 */
		for (String student : students) {
			if (student.startsWith("B")) {
				System.out.println(student.toUpperCase());
			}
		}
		
		/*
		 * Display name in uppercase that start with B
		 * using declarative style
		 */
		students.stream()
				.filter(name -> name.startsWith("B"))
				.map(name -> name.toUpperCase())
				.forEach(System.out::println);
		
		Optional<String> value = students.stream()
				.filter(name -> name.startsWith("K"))
				.map(name -> name.toUpperCase())
				.findFirst();
		
		value.ifPresent(System.out::println);
		System.out.println(Optional.empty().isPresent()); 	// false
		System.out.println(Optional.empty().orElse("raz"));	// raz
	}
}

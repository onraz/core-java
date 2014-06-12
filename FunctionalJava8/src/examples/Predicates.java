package examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Predicates {
	
	static Predicate<String> checkStartsWith(String letter) {
		return name -> name.startsWith(letter);
	}
	
	static void main(String... args) {
		List<String> names = Arrays.asList("Raz", "Rahils", "Bob", "Peter", "Don", "Mohammed");

		// direct lambda
		names.stream()
			.filter(name -> name.startsWith("R"))
			.forEach(System.out::println);
		
		// predicate lambda
		Predicate<String> startsWithR = name -> name.startsWith("R");
		names.stream()
				.filter(startsWithR)
				.forEach(System.out::println);
		
		// Reusable lambda
		names.stream()
				.filter(checkStartsWith("R"))
				.forEach(System.out::println);
		
		// Reusable lambda factory - verbose
		Function<String, Predicate<String>> startsWithLetter = (String letter) -> {
			Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
			return checkStarts; 
		};
		
		// Reusable lambda factory - concise - **Preferable**
		Function<String, Predicate<String>> startsWith = letter -> name -> name.startsWith(letter);
		
		names.stream()
				.filter(startsWithLetter.apply("R"))
				.forEach(System.out::println);
		
		names.stream()
				.filter(startsWith.apply("R"))
				.forEach(System.out::println);
		
		names.stream()
			.reduce((x, y) -> x.length() > y.length() ? x : y)
			.ifPresent(System.out::println);
		
		System.out.println(
			names.stream()
				.collect(Collectors.joining(", "))
		);		
	}
}

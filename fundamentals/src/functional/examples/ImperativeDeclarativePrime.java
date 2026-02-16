package functional.examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

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
public interface ImperativeDeclarativePrime {
	
	/**
	 * A prime number is a positive integer greater than 1 
	 * that is only divisible by 1 and itself
	 * 
	 * @param num
	 * @return
	 */
	static boolean isPrimeImperative(int num) {
		boolean prime = true;
		if (num > 1) {
			for (int divisor = 2; divisor < num; divisor++) {
				if (num % divisor == 0) {
					prime = false;
					break;
				}
			}
		} else {
			prime = false;
		}
		return prime;
	}
	
	/**
	 * Determine Prime using declarative functional style
	 * 
	 * @param n
	 * @return
	 */
	static boolean isPrimeDeclarative(int n) {
		return n > 1 && IntStream.range(2, n)
				.noneMatch(index -> n % index == 0);
	}
	
	static void main(String[] args) {
		List<Integer> values = Arrays.asList(10, 3, 25, 30, 44, 7);
		
		/*
		 * i. Find Prime numbers in a List imperatively
		 */
		for (Integer num : values) {
			if (isPrimeImperative(num)) {
				System.out.println(num);
			}
		}
		
		/*
		 * ii. Find Prime numbers in a List declaratively 
		 */
		values.stream()
				.filter(ImperativeDeclarativePrime::isPrimeDeclarative)
				.forEach(System.out::println);
		/*
		 * iii. Another advantage of functional style is that
		 * 		we can apply concurrency safely
		 */
		values.parallelStream()
				.filter(ImperativeDeclarativePrime::isPrimeDeclarative)
				.forEach(System.out::println);
	}
	
	/**
	 * Alternative style dissecting the lambda
	 * 
	 * @param n
	 * @return
	 */
	static boolean isPrimeDeclarativeLong(int n) {
		// The lambda has lexical scope, can access n
		IntPredicate isNumDivisibleBy = divisor -> n % divisor == 0;
		return n > 1 && IntStream.range(2, n)
				.noneMatch(isNumDivisibleBy);
	}	
}

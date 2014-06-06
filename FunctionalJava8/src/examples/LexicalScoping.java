package examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LexicalScoping {
	
	/**
	 * Given a divisor, return a function that determines whether a given 
	 * number is divisible by the divisor
	 * 
	 *  Note: The return type is lexically bound to the divisor parameter
	 *  Its available even after the isDivisibleBy method returns.
	 */
	static Predicate<Integer> isDivisibleBy(int divisor) {
		return num -> num % divisor == 0;
	}
	
	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(23, 35, 17, 27, 99, 105, 339);
		
		Predicate<Integer> isDivisibleBy3 = isDivisibleBy(3);
		Predicate<Integer> isDivisibleBy5 = isDivisibleBy(5);
		
		// print numbers divisible by 3
		System.out.println("Numbers Divisible by 3:");
		values.stream()
				.filter(isDivisibleBy3)
				.forEach(System.out::println);
		
		// print numbers divisible by 5
		System.out.println("Numbers Divisible by 5:");
		values.stream()
				.filter(isDivisibleBy5)
				.forEach(System.out::println);
	}
}

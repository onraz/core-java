package examples.functional;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class IsPrime {

	static boolean isPrime(int number) {
		IntPredicate isDivisible = divisor -> number % divisor == 0;
		return number > 1 && IntStream.range(2, number)
				.noneMatch(isDivisible);
	}

	public static void main(String[] args) {
		System.out.println("isPrime(5): " + isPrime(5));
		System.out.println("isPrime(10): " + isPrime(10));
		System.out.println("isPrime(11): " + isPrime(11));
	}
}

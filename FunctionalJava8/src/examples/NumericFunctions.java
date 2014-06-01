package examples;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public interface NumericFunctions {
	
	static boolean isPrime(int number) {
		IntPredicate isDivisible = divisor -> number % divisor == 0;
		return number > 1 && IntStream.range(2, number)
				.noneMatch(isDivisible);
	}
}

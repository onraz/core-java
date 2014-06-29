package examples;

import java.util.stream.IntStream;

public interface TailRecursion {

	public static int factorial(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}

	static void main(String[] args) {
		IntStream.range(1, 10)
				.map(TailRecursion::factorial)
				.forEach(System.out::println);

	}
}

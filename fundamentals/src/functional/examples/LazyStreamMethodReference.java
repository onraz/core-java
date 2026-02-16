package functional.examples;

import java.util.Arrays;
import java.util.List;

/**	Focus: Lazy Evaluation
 * 
 *  Java 8 optimizes how stream can be used, for instance,
 *  the following code doesn't loop through all numbers, only the
 *  ones in the list
 */
public class LazyStreamMethodReference {

	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(-3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		/* Contrast the imperative style
		 * See how the control structures distract the reader from 
		 * understanding the essence of the solution
		 */
		int result = 0;
		for (int elem : values) {
			if (elem > 0 && elem % 2 == 0) {
				result = elem * elem;
				break;
			}
		}
		System.out.println(result);
		
		/*
		 * The functional style focuses on the what, not the how
		 * Since how is not specified, compiler can optimize it
		 */
		values.stream()
			.filter(Util::isPositive)
			.filter(Util::isEven)
			.map(Util::square)
			.findFirst()
			.ifPresent(System.out::println);
		
		/* Output:

			# Called isPositive: -3
			# Called isPositive: 1
			## Called isEven: 1
			# Called isPositive: 2
			## Called isEven: 2
			### Called Square: 2
			4
			
		 */
	}
	
	interface Util {
		static boolean isPositive(int n) {
			System.out.println("# Called isPositive: " + n);
			return n > 0;
		}

		static boolean isEven(int n) {
			System.out.println("## Called isEven: " + n);
			return n % 2 == 0;
		}

		static int square(int n) {
			System.out.println("### Called Square: " + n);
			return n * n;
		}
	}	
}

package examples;

import java.util.Arrays;
import java.util.List;

/**
 * Dependency Injection is a form of the Strategy Pattern
 * In this context, it forces us to have different objects
 * "which is like ceremonial programming" 
 *
 */
interface Selector {
	boolean pick(int value);
}

class EvenSelector implements Selector {
	@Override
	public boolean pick(int value) {
		return value % 2 == 0;
	}
}

public class TotalCalculationFunctional {
	
	/**
	 * Object Oriented Style with Strategy pattern
	 * 
	 * Selector could be changed with Predicate<Integer>
	 * 
	 * @param values
	 * @param selector
	 * @return
	 */
	static int totalOfDouble(List<Integer> values, Selector selector) {
		int result = 0;
		for (Integer elem : values) {
			if (selector.pick(elem)) {
				result += (elem * elem);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(1, 2, 4, 5, 9, 10, 11);
		/*
		 * The following uses imperative style with strategy pattern 
		 */
		System.out.println(totalOfDouble(values, new EvenSelector()));
		System.out.println(totalOfDouble(values, new Selector() {
			@Override
			public boolean pick(int value) {
				return value % 2 != 0;
			}
		}));
		
		/*
		 * Contrast the above solution with a function style
		 */
		System.out.println(values.stream()
				.filter(e -> e % 2 == 0)
				.mapToInt(e -> e * e)
				.sum() 
		);
		
		/*
		 * Alternatively, the stream can be reduced using function
		 */
		System.out.println(values.stream()
								.filter(e -> e % 2 == 0)
								.map(e -> e * e)
								.reduce(0, (x, y) -> x + y) // Math::addExact could be used
		);
	}
}

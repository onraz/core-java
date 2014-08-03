package core.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(70, 30, 5, 45, 90, 24, 5, 12, 5);
		
		// IN-PLACE sort, reverseOrder Sort
		Collections.sort(list);
		System.out.println("Sorted" + list);

		Collections.sort(list, Collections.reverseOrder());
		System.out.println("Sorted" + list);

		// min, max
		System.out.println(Collections.max(list));
		System.out.println(Collections.min(list));

		// Frequency Count
		System.out.println(Collections.frequency(list, 5));

		// Singleton List
		List<Integer> singletonList = Collections.singletonList(30);
		System.out.println(singletonList);

		// Immutable List
		Collections.unmodifiableList(Arrays.asList(10, 20, 30));
	}
}

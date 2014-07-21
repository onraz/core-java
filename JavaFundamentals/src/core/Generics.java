package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The primary use case of Generics is to allow Generic Algorithms
 * It also gets rid of casting when retrieving objects from collections
 *
 */
public interface Generics {
	
	
	/*
	 * Type Parameters on Generic Classes/Interfaces/Methods Only support Upper Bound (extends)
	 * Type parameters can't be bound by final types such as <T extends Integer>
	 * 
	 * Generic type parameters can be used in multiple upper bounds:
	 * 	e.g. class Box <T extends classA & interfaceB & interfaceC>
	 * 
	 */
	class Pair<K extends Number, V extends Number> {
		
		private K key;
		private V value;
		
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		K getKey() { return key; }
		V getValue() { return value; }
		
		boolean hasEvenValue() { return value.intValue()  % 2 == 0; }
		
		static <S extends Number> S max(S x, S y) {
			// can't do x > y as the operator > is only defined for primitives
	        return x.intValue() > y.intValue() ? x : y;
	    }
		
		/**
		 * Find the max of a list in a generic fashion
		 * Note: Type Serializable not required, added just to illustrate Multi-bounds
		 * @param list
		 * @return
		 */
		static <T extends Comparable<T> & Serializable> T max(List<T> list) {
			T maxElem = list.get(0);
			for (int begin = 0; begin < list.size(); ++begin)
				if (maxElem.compareTo(list.get(begin)) < 0)
					maxElem = list.get(begin);
			return maxElem;
		}
		
		static {
			// demonstrate above generic max
			List<Integer> list = Arrays.asList(99, 2, 3, 4);
			Integer maxItem = max(list);
			System.out.println("Max item is: " + maxItem);
			
			List<String> list2 = Arrays.asList("abc", "dcc", "zab", "raz");
			String maxItem2 = max(list2);
			System.out.println("Max item is: " + maxItem2);
		}
		
		public <T, U extends Number> T buildPair(T key, U given, V exist) {
			/*
			 * We can't convert from U to V although both extends Number
			 * This is because, at runtime buildPair(double) can be called
			 * on a Pair<Integer>
			 */
			// this.value = given;  
			this.value = exist;
			System.out.println(value.intValue() * given.intValue() * exist.intValue());
			return key;
		}
		
		/**
		 * Converting the above method to static, we can no longer access K,V
		 * This is the only difference between static and instance generic methods
		 */
		public static <T, U extends Number> T buildPair(T key, U given) {
			System.out.println(given.intValue());
			return key;
		}
		
		/**
		 * Wildcards (?) can only be used in parameter, field, local variable, and return types
		 * Wildcards support three modes
		 * 		- Upper Bound: 	Type<? extends OtherType>
		 * 		- Un Bounded:	Type<?>
		 * 		- Lower Bound:	Type<? super OtherType 
		 * @param list
		 */
		public void printList(List<? extends Number> list) {
			for (Number n : list) {
				System.out.println(n);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	static void main(String... args) {
		// compile time check that all elements are <Integer>
		List<Integer> myList = new ArrayList<>();
		myList.add(3);
		// myList.add("raz"); won't work - compile time check
		
		@SuppressWarnings("rawtypes")
		List rawList = myList;
		rawList.add("raz");
		try {
			for (Integer num : myList) {
				System.out.println(num);
			}
		} catch (ClassCastException ex) {
			System.out.println("Generics can't detect errors at runtime!");
			ex.printStackTrace();
		}
		
		List<Pair<Integer, Double>> boxList = new ArrayList<>();
		boxList.add(new Pair<>(3, 5.5));
		
		
		/*
		 *  Diamond Operator Inference
		 *  	Allows us to omit type arguments on the right
		 */
		List<String> list = new ArrayList<>();
		list.add("A");
		
		// The following statement should fail since addAll expects
		  // Collection<? extends String>

		list.addAll(new ArrayList<>());
		System.out.println(list);
		
	}
	
}

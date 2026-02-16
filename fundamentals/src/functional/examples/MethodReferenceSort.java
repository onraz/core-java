package functional.examples;

import java.util.Arrays;
import java.util.Comparator;

import functional.domain.Person;

public interface MethodReferenceSort {

	static void main(String... args) {
		Person[] roster = Person.getRoster();
		
		/*
		 * Sorting using traditional Comparator Interface
		 */
		Arrays.sort(roster, new Comparator<Person>() {
			@Override
			public int compare(Person a, Person b) {
				return Person.compareByAge(a, b);
			}
		});
		
		/*
		 * Sorting using lambdas to reduce boilerplate
		 */
		Arrays.sort(roster, (Person a, Person b) -> Person.compareByAge(a, b));
		
		/*
		 * Use method expressions to further reduce the code
		 */
		Arrays.sort(roster, Person::compareByAge);
		
		for (Person p : roster) {
			System.out.println(p);
		}
		
		/*
		 * Using Streams
		 */
		Arrays.stream(roster)
				.filter(person -> person.getName().startsWith("R"))
				.forEach(System.out::println);
		
	}
}

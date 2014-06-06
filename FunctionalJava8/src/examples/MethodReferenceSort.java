package examples;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public interface MethodReferenceSort {

	class Person {
		String name;
		LocalDate birthday;

		public Person(String name, LocalDate birthday) {
			this.name = name;
			this.birthday = birthday;
		}
		
		public int getAge() {
			return birthday.until(LocalDate.now()).getYears();
		}

		public static int compareByAge(Person a, Person b) {
			return a.birthday.compareTo(b.birthday);
		}

		public String toString() {
			return name + ", " + getAge();
		}
	}

	static void main(String... args) {
		Person[] roster = new Person[]{
					new Person("Gill", LocalDate.of(1981, 2, 17)),
					new Person("Bob", LocalDate.of(1985, 8, 3)),
					new Person("Tim", LocalDate.of(1971, 5, 15)),
					new Person("Ray", LocalDate.of(1971, 11, 29))
		};
		
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
				.filter(person -> person.name.startsWith("R"))
				.forEach(System.out::println);
		
	}
}

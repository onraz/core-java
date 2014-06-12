package examples;

import java.util.Arrays;

import domain.Person;

public interface ConcurrentReduction {

	static void main(String... args) {
		Person[] roster = Person.getRoster();
		
		Arrays.stream(roster)
			.filter(p -> p.getSex() == Person.Sex.FEMALE)
			.forEach(System.out::println);
		
		Arrays.stream(roster)
		.filter(p -> p.getSex() == Person.Sex.FEMALE)
		.mapToInt(Person::getAge)
		.average()
		;

	}
}

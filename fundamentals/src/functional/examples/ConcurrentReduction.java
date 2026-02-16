package functional.examples;

import java.util.Arrays;

import functional.domain.Person;

public interface ConcurrentReduction {

	static void main1(String... args) {
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

	public static void main(String[] args) {
		Person[] roster = Person.getRoster();

		Arrays.stream(roster).mapToInt(Person::getAge).average();
	}
}

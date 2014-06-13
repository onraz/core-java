package examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import domain.Person;

public class Comparators {
	public static void main(String[] args) throws IOException {
		Arrays.stream(Person.getRoster())
				.sorted((person1, person2) -> person1.getAge() - person2.getAge())
				.forEach(System.out::println);
		
		Comparator<Person> personAscending = (person1, person2) -> person1.getAge() - person2.getAge();
		/*
		 * This is an example of decorator pattern, by decorating:
		 * int compare(t1, t2) {
		 * 	return decorated.compare(t2, t1);
		 * }
		 * 
		 */
		Comparator<Person> personDescending = personAscending.reversed();
		Arrays.stream(Person.getRoster())
			.sorted(personDescending)
			.forEach(System.out::println);
		
		Arrays.stream(Person.getRoster())
			.min(personAscending)
			.ifPresent(System.out::println);
		
		Files.list(Paths.get("."))
			.filter(Files::isDirectory)	
			.forEach(System.out::println);;
	}
}

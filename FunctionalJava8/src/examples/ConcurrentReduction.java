package examples;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

import domain.Person;

public interface ConcurrentReduction {

	static void main(String... args) {
		Person[] roster = new Person[] {
				new Person("Gill", Person.Sex.FEMALE, LocalDate.of(1981, 2, 17)),
				new Person("Bob", Person.Sex.MALE, LocalDate.of(1985, 8, 3)),
				new Person("Tim", Person.Sex.MALE, LocalDate.of(1971, 5, 15)),
				new Person("Ren", Person.Sex.FEMALE, LocalDate.of(1971, 11, 29)) 
		};

	}
}

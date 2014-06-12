package domain;

import java.time.LocalDate;

public class Person {
	
	public static enum Sex { MALE, FEMALE };
	
	String name;
	Sex sex;
	LocalDate birthday;
	

	public String getName() {
		return name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public Person(String name, Sex sex, LocalDate birthday) {
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
	}
	
	public int getAge() {
		return birthday.until(LocalDate.now()).getYears();
	}

	public static int compareByAge(Person a, Person b) {
		return a.birthday.compareTo(b.birthday);
	}

	public Sex getSex() {
		return sex;
	}
	
	public String toString() {
		return name + ", " + sex + " Age: " + getAge();
	}
	
	public static Person[] getRoster() {
		return new Person[]{
				new Person("Gill", Person.Sex.FEMALE, LocalDate.of(1981, 2, 17)),
				new Person("Bob", Person.Sex.MALE, LocalDate.of(1985, 8, 3)),
				new Person("Tim", Person.Sex.MALE, LocalDate.of(1971, 5, 15)),
				new Person("Ren", Person.Sex.FEMALE, LocalDate.of(1971, 11, 29))
		};
	}
}
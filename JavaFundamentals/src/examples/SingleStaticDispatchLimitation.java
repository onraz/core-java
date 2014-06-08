package examples;

import java.util.Arrays;
import java.util.List;

public interface SingleStaticDispatchLimitation {

	class Animal {
		public void meet(Animal animal) {
			System.out.println("Inside Animal. I am " + shortClass(this) + ", you are " + shortClass(animal));
		}
		
		String shortClass(Object obj) {
			return obj.getClass().toString().substring(46);
		}
	}
	class Cow extends Animal {
		public void meet(Cow animal) {
			System.out.println("Hi Cow - how are you! Moo ");
		}
	}
	class Lion extends Animal {
		public void meet(Lion animal) {
			System.out.println("Roar Im lion, hi ");
		}
	}
	
	static void main(String... args) {
		
		/*
		 * Same object different behavior! Static Dispatch!
		 */
		Lion lion = new Lion();
		lion.meet(lion);
		
		Animal myAnimal = lion;
		myAnimal.meet(lion);
		
		
		/*
		 * Behavior gets even more hidden in lists due to static dispatch 
		 */
		List<Animal> animals = Arrays.asList(new Animal(), lion, new Cow());
		List<Animal> friends = Arrays.asList(new Animal(), lion, new Cow());
		for (Animal animal : animals) {
			for (Animal friend : friends) {
				animal.meet(friend);
			}
		}
		
	}
}

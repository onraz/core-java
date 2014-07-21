package core;

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
		
		public void meetMultiDispatch(Animal animal) {
			if (animal.getClass() == Animal.class) {
				if (this.getClass() == Animal.class) {
					this.meet(animal);
				} else if (this.getClass() == Lion.class) {
					((Lion)this).meet(animal);
				} else if (this.getClass() == Cow.class) {
					((Cow)this).meet(animal);
				}
			} else if (animal.getClass() == Lion.class) {
				if (this.getClass() == Animal.class) {
					this.meet((Lion)animal);
				} else if (this.getClass() == Lion.class) {
					((Lion)this).meet((Lion)animal);
				} else if (this.getClass() == Cow.class) {
					((Cow)this).meet((Lion)animal);
				}
			} else if (animal.getClass() == Cow.class) {
				if (this.getClass() == Animal.class) {
					this.meet((Cow)animal);
				} else if (this.getClass() == Lion.class) {
					((Lion)this).meet((Cow)animal);
				} else if (this.getClass() == Cow.class) {
					((Cow)this).meet((Cow)animal);
				}
			}
		}
		
	}
	
	class Cow extends Animal {
		public void meet(Cow animal) {
			System.out.println("Hi COW@@ - how are you! Moo " + shortClass(animal));
		}
		
		public void meet(Lion animal) {
			System.out.println("Scary LION@@ - how are you! Moo " + shortClass(animal));
		}
	}
	
	class Lion extends Animal {
		
		public void meet(Lion animal) {
			System.out.println("Roar Im lion, LION!! " + shortClass(animal));
		}
		
		public void meet(Cow animal) {
			System.out.println("Roar Im lion, COW!! " + shortClass(animal));
		}
	}
	
	static void main(String... args) {
		
		/*
		 * Same object different behavior! Static Dispatch!
		 * Multi dispatch used both the instance and argument type to determine which one to use
		 */
		Lion lion = new Lion();
		lion.meet(lion);
		lion.meet(new Animal());
		
		Animal myAnimal = lion;
		myAnimal.meet(lion);
		
		
		
		System.out.println("Single Dispatch.......ALWAYS BASE CLASS as compiled types");
		/*
		 * Single Dispatch
		 * Behavior gets even more hidden in lists due to static dispatch 
		 */
		List<Animal> animals = Arrays.asList(new Animal(), lion, new Cow());
		List<Animal> friends = Arrays.asList(new Animal(), lion, new Cow());
		for (Animal animal : animals) {
			for (Animal friend : friends) {
				animal.meet(friend);
			}
		}
		
		
		System.out.println("Multi Dispatch.......COMBINES instance and parameter runtime types");
		/*
		 * this is how multi dispatch works
		 */
		for (Animal animal : animals) {
			for (Animal friend : friends) {
				animal.meetMultiDispatch(friend);
			}
		}
		
	}
}

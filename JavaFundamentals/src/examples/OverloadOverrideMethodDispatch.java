package examples;

/**
 * 
 * Override - dynamic, runtime dispatch based on instance type at runtime for object hierarchy.
 * Method signature must be identical, return type/exception can be narrowed. Visibility can be widened
 * 
 * Overload - Static, compile time dispatch based on Same method name with different signatures.
 * Method signature must vary - No restrictions on return type/exception types BUT return type/exception alone 
 * can't be changed. No restriction on Visibility. 
 * Varargs can't be used to overload a with the same signature using arrays
 */
public class OverloadOverrideMethodDispatch {
	class Animal {
		public Animal talk(Animal animal) {
			System.out.println("Animal talking to " + animal );
			return animal;
		}
		public Animal eat(Animal animal) throws Exception {
			System.out.println("Animal is eating");
			return this;
		}
		
		public Animal sleep(Animal animal) throws InterruptedException {
			System.out.println("Sleeping peacefully animal");
			return animal;
		}
	}
	
	class Cow extends Animal {
		/**
		 * Example of override - this is because the method
		 * signature is identical to the parent class
		 * 	Only method name and parameters are part of the signature: eat(Animal)
		 * 	Subclasses of parameters are considered different parameters
		 * 	
		 * Return type can vary in the direction of Subclass - this is known as Covariant Return type
		 * 		for instance we can return a calf
		 * 
		 * Exception can also vary - subclass or none - so one can send more generalised exception or no
		 * exception
		 */
		@Override
		public Cow eat(Animal animal) throws RuntimeException {
			System.out.println("Cow is Eating");
			return this;
		}
		
		/*
		 * Notice how Exception can be narrowed to "No Exceptions"
		 * As a method that can throw an exception may not generate one if application runs ok 
		 */
		@Override
		public Animal sleep(Animal animal) {
			return new Cow();
		}
		
		/**
		 * Overloaded methods have the same name but different signature - 
		 * although parameters are just subclasses
		 * 
		 * Return types ALONE can't be used for overloading and must remain the same!
		 * 
		 * if the parameter lists differ, and can be resolved by the compiler, 
		 * then each Overloaded method MAY return a different type
		 * 
		 * @param cow
		 */
		public Cow talk(Cow cow) {
			System.out.println("Cow talking to " + cow);
			return cow;
		}
		
		/* 
		 * The following is NOT possible - SAME signature but DIFFERENT return type
		 * The compiler has no way of resolving it statically.
		 *  
			public Animal talk(Cow cow) {
				System.out.println("Cow talking to " + cow);
				return cow;
			}
		 */
		public Animal talk(Cow cow, Animal animal) {
			System.out.println("Cow talking to animal" + animal);
			return cow;
		}
		
		/* 
		 * This is possible as compiler can identify this method uniquely based on parameters 
		 */
		public Cow talk(Animal animal, Cow cow) {
			System.out.println("Cow talking to animal" + animal);
			return cow;
		}
		
	}
}

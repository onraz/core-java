package core;

import java.util.ArrayList;
import java.util.List;

/**
 * A top level class is a class that is not a nested class.
 * A nested class is any class whose declaration occurs within the body of another class or interface.
 * 
 * An inner class is a nested class that is not explicitly or implicitly declared static.
 * Inner classes include:
 *		- local, 
 * 		- anonymous and 
 * 		- non-static member classes.
 * 
 * @author Raz
 *
 */
class HasStatic {
    static int j = 100;
}

public class InnerOuterTypes {
	
	public static void main(String[] args) {
		// inner nested class
		class Rahil extends HasStatic { }
		
		// enums cant be nested IN a METHOD
		// enum Sex { Male, Female };
		
		// interfaces cant be nested IN a METHOD
	    // interface NeverInnerButNested2 {}
		
		List<Object> razs = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			// inner nested class
			class Raz extends Rahil {
			}
			razs.add(new Raz());
		}
		razs.add(new Rahil());
		razs.stream().forEach(System.out::println);
	}
	
	// inner nested class
	class Inner extends HasStatic {
        static final int x = 3;  // OK: compile-time constant
        // static int y = 4;  // Compile-time error: an inner class
        // enum Sex {Male, Female}; // enums are implicitly static so can't be declared in inner
        /* 
         * Static initialisers are not allowed
        static {
        	System.out.println(y);
        }
        */
        public Inner() {
        	// can access static members from outerclass or inherited class
        	j = 10;
        }
        {
        	System.out.println(j);
        }
    }
	
	// nested class
    static class NestedButNotInner{
        static int y = 4;    // OK: not an inner class
        enum Sex { Male, Female }; // OK: Enums are implicitly static, so accepted under Static Nested class
        static {
        	System.out.println(y);
        }
    }
    
    // nested interface type
    // Interfaces are never inner - they are IMPLICITLy static
    /*static*/ interface NeverInnerButNested {
    	static int x = 10;
    }
    
    // just like interface, implicitly static
    /*static*/ enum Sex {Male, Female};
    
    // Cant define inner enum/interface in an initialisation block whether static or instance
    // INIT blocks are effectively METHODS - Same rules APPLY
    static {
    	
    	/* Just like a method */
    	
    	// interface Toy {};
    	// enum Toy {};
    	class Toy {
    		
    	}
    	
    	new Toy();
    }
}

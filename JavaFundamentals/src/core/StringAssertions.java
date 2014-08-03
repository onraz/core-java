package core;

public class StringAssertions {
	public static void main(String[] args) {

		String name = new String("Rahil");
		String name2 = new String("Rahil");
		String name3 = "Rahil";
		
		System.out.println(name == name2);
		System.out.println(name == name3);
		
		String name4 = "Rahil";
		// as the compiler has interned the string
		System.out.println(name4 == name3);
		
		String name5 = name.intern();
		System.out.println(name5 == name4);
		
	}
}

package examples;

import static examples.NumericFunctions.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class NumericTests {

	@Test
	public void testIsPrime() {
		assertFalse(isPrime(1));
		assertFalse(isPrime(4));
		assertTrue(isPrime(5));
		assertTrue(isPrime(11));
		assertTrue(isPrime(23));
		assertFalse(isPrime(205));
	}

}

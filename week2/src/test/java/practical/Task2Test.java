package practical;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class Task2Test {

	@Test
	public void testFirstStatement() {
		Task2 test1 = new Task2();
		assertEquals("2, 5",test1.sort(2, 5));
	}
	
	@Test
	public void testSecondStatement() {
		Task2 test1 = new Task2();
		assertEquals("4, 7",test1.sort(7, 4));
	}
	
	@Test
	public void testThirdStatement() {
		Task2 test1 = new Task2();
		assertEquals("5",test1.sort(5, 5));
	}

	@Test
	public void testNegativeNumbers() {
		Task2 test1 = new Task2();
		assertEquals("-2, 5",test1.sort(-2, 5));
	}
}

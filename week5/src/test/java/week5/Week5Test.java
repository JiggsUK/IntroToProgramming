package week5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class Week5Test {

	@Test
	void testTask2aNotNull() {
		Week5 test = new Week5();
		List<Integer> testList = new ArrayList<Integer>(test.task2a());
		assertNotNull(testList);
	}
	
	@Test
	void testTask2aNumberOfElements() {
		Week5 test = new Week5();
		List<Integer> testList = new ArrayList<Integer>(test.task2a());
		assertEquals(36, testList.size());
	}
	
	@Test
	void testTask2a() {
		Week5 test = new Week5();
		test.task2a();
	}
	
	@Test
	List<Integer> testTask2b() {
		Week5 test = new Week5();
		List<Integer> testList = new ArrayList<Integer>(test.task2b(test.task2a()));
		return testList;
	}
	
	@Test
	void testFibArrayOutput() {
		Week5 test = new Week5();
		test.fib();
	}
}

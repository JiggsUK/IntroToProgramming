package practical;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Task3Test {

	@Test
	public void testTriangle() {
		Task3 triangle = new Task3();
		assertEquals(5, triangle.areaoftriangle(2, 5));
	}
	
	@Test
	public void testSquare() {
		Task3 square = new Task3();
		assertEquals(25, square.areaofsquare(5));
	}
	
	@Test
	public void testRectangle() {
		Task3 rectangle = new Task3();
		assertEquals(10, rectangle.areaofrectangle(2, 5));
	}
	
	@Test
	public void testCircle() {
		Task3 circle = new Task3();
		assertEquals(78, circle.areaofcircle(5));
	}
	
	@Test
	public void testTriangleSelector() {
		Task3 triangle = new Task3();
		assertEquals(5, triangle.shapeselector('t', 2, 5));
	}
	
	@Test
	public void testSquareSelector() {
		Task3 square = new Task3();
		assertEquals(25, square.shapeselector('s', 5, 0));
	}
	
	@Test
	public void testRectangleSelector() {
		Task3 rectangle = new Task3();
		assertEquals(10, rectangle.shapeselector('r', 2, 5));
	}
	
	@Test
	public void testCircleSelector() {
		Task3 circle = new Task3();
		assertEquals(78, circle.shapeselector('c', 5, 0));
	}
}

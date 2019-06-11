package practical;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Task3TestSwitch {

	@Test
	public void testTriangle() {
		Task3Switch triangle = new Task3Switch();
		assertEquals(5, triangle.areaoftriangle(2, 5));
	}
	
	@Test
	public void testSquare() {
		Task3Switch square = new Task3Switch();
		assertEquals(25, square.areaofsquare(5));
	}
	
	@Test
	public void testRectangle() {
		Task3Switch rectangle = new Task3Switch();
		assertEquals(10, rectangle.areaofrectangle(2, 5));
	}
	
	@Test
	public void testCircle() {
		Task3Switch circle = new Task3Switch();
		assertEquals(79, circle.areaofcircle(5));
	}
	
	@Test
	public void testTriangleSelector() {
		Task3Switch triangle = new Task3Switch();
		assertEquals(5, triangle.shapeselector('t', 2, 5));
	}
	
	@Test
	public void testSquareSelector() {
		Task3Switch square = new Task3Switch();
		assertEquals(25, square.shapeselector('s', 5));
	}
	
	@Test
	public void testRectangleSelector() {
		Task3Switch rectangle = new Task3Switch();
		assertEquals(10, rectangle.shapeselector('r', 2, 5));
	}
	
	@Test
	public void testCircleSelector() {
		Task3Switch circle = new Task3Switch();
		assertEquals(82, circle.shapeselector('c', 5.1167));
	}
}

package week6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Week6Test {

//	@Test
//	void testRectangleArea() {
//		Rectangle rec = new Rectangle();
//		rec.height = 2;
//		rec.width = 3;
//		assertEquals(6, rec.area());
//	}
//	
//	@Test
//	void testRectangleCorner() {
//		Rectangle rec = new Rectangle();
//		assertEquals(4, rec.corner);
//	}
//	
//	@Test
//	void testRectanglePerimeter() {
//		Rectangle rec = new Rectangle();
//		rec.height = 2;
//		rec.width = 3;
//		assertEquals(12, rec.perimeter());
//	}
//	
//	
//	@Test
//	void testCircleArea() {
//		Circle circ = new Circle();
//		circ.radius = 2;
//		assertEquals(12, circ.area());
//	}
//	
//	@Test
//	void testCircleCorner() {
//		Circle circ = new Circle();
//		assertEquals(0, circ.corner);
//	}
//	
//	@Test
//	void testCirclePerimeter() {
//		Circle circ = new Circle();
//		circ.radius = 2;
//		assertEquals(12, circ.perimeter());
//	}
//	
//	@Test
//	void testSquareCorner() {
//		Square squ = new Square();
//		assertEquals(4, squ.corner);
//	}
//	
//	@Test
//	void testSquarePerimeter() {
//		Square squ = new Square();
//		squ.height = 4;
//		assertEquals(16, squ.perimeter());
//	}
//	
//	@Test
//	void testSquareArea() {
//		Square squ = new Square(2);
//		squ.height = 2;
//		assertEquals(4, squ.area());
//	}
//	
//	@Test
//	void testTriangleCorner() {
//		Triangle tri = new Triangle(3);
//		assertEquals(3, tri.corner);
//	}
//	
//	@Test
//	void testTrianglePerimeter() {
//		Triangle tri = new Triangle(3, 4, 5);
//		tri.base = 3;
//		tri.side2 = 4;
//		tri.side3 = 5;
//		assertEquals(12, tri.perimeter());
//	}
//	
//	@Test
//	void testTriangleArea() {
//		Triangle tri = new Triangle(2, 3);
//		tri.height = 2;
//		tri.base = 3;
//		assertEquals(3, tri.area());
//	}
//	
	@Test
	void testShapesCornerSum() {
		Shapes cornerSum = new Shapes();
		assertEquals(11, cornerSum.corner());
	}
	
	@Test
	void testShapesAreaSum() {
		Shapes areaSum = new Shapes();
		assertEquals(512, areaSum.area());
	}
	
	@Test
	void testShapesPerimeterSum() {
		Shapes perimeterSum = new Shapes();
		assertEquals(132, perimeterSum.perimeter());
	}
}

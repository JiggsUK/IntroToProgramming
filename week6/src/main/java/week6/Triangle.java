package week6;

public class Triangle extends Shape {
	
	int height;
	int base;
	int side2;
	int side3;
	
	public Triangle(int height, int base, int side2, int side3) {
		this.height = height;
		this.base = base;
		this.side2 = side2;
		this.side3 = side3;
		this.corner = 3;
	}

	@Override
	public int area() {
		return (height * base)/2;
	}

	@Override
	public int perimeter() {
		return base + side2 + side3;
	}

	
}

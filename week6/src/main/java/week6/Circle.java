package week6;

public class Circle extends Shape {
	
	int radius;
	
	public Circle(int radius) {
		this.corner = 0;
		this.radius = radius;
	}

	@Override
	public int area() {
		return (int) (Math.PI * (Math.pow(radius, 2)));
	}

	@Override
	public int perimeter() {
		return (int) (2 * Math.PI * radius);
	}

}

package week6;

public class Square extends Shape {
	
	int height;
	
	public Square(int height) {
		this.height = height;
		this.corner = 4;
	}
	
	@Override
	public int area() {
		return (int) Math.pow(height, 2);
	}

	@Override
	public int perimeter() {
		return 4 * height;
	}


	
}

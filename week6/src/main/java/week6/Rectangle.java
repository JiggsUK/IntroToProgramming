package week6;

public class Rectangle extends Shape{
	
	int height;
	int width;
	
	public Rectangle(int height, int width) {
		this.height = height;
		this.width = width;
		this.corner = 4;
	}

	@Override
	public int area() {
		return height * width;
	}

	@Override
	public int perimeter() {
		return 2 * (height * width);
	}

	
	
}

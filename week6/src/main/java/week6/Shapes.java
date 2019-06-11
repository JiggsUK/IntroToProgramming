package week6;

import java.util.ArrayList;

public class Shapes extends Shape {
	
	private ArrayList<Shape> shapesList = new ArrayList<Shape>();
	
	public Shapes() {
		shapesList.add(new Square(7));
		shapesList.add(new Triangle(1, 2, 3, 4));
		shapesList.add(new Circle(12));
		shapesList.add(new Rectangle(2, 5));
	}
	
	public int corner() {
		int sum = 0;
		for (Shape element : shapesList) {
			sum += element.corner;
		}
		System.out.println(sum);
		return sum;
	}
	
	@Override
	public int area() {
		int sum = 0;
		for (Shape element : shapesList) {
			sum += element.area();
		}
		System.out.println(sum);
		return sum;
	}

	@Override
	public int perimeter() {
		int sum = 0;
		for (Shape element : shapesList) {
			sum += element.perimeter();
		}
		System.out.println(sum);
		return sum;
	}
}

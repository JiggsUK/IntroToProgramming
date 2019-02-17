package practical;

public class Task3Switch {
		
	public int areaoftriangle(int h, int w) {
		int triArea = (h * w)/2;
		return triArea;
	}
	
	public double areaofsquare(double a) {
		double sqArea = Math.pow(a, 2);
		return sqArea;
	}
	
	public int areaofrectangle(int w, int l) {
		int recArea = w * l;
		return recArea;
	}
	
	public double areaofcircle(double r) {
		double circArea = Math.PI * (Math.pow(r, 2));
		return Math.round(circArea);
	}
	
	//changed shapeselector method to use switch-case and method overloading
	public int shapeselector(char shape, int a, int b) {
		
		int selection = 0;
		
		switch (shape) {
			case 't':
				selection = areaoftriangle(a, b);
				break;
			case 'r':
				selection = areaofrectangle(a, b);
				break;
							
		}
		return selection;
	}
	
	public double shapeselector(char shape, double a) {
		
		double selection = 0;
		
		switch (shape) {
			case 's':
				selection = areaofsquare(a);
				break;
			case 'c':
				selection = areaofcircle(a);
				break;
		}
		return selection;
	}
}

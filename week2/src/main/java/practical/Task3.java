package practical;

public class Task3 {
		
	public int areaoftriangle(int h, int w) {
		int triArea = (h * w)/2;
		return triArea;
	}
	
	public int areaofsquare(int a) {
		int sqArea = (int) Math.pow(a, 2);
		return sqArea;
	}
	
	
	public int areaofrectangle(int w, int l) {
		int recArea = w * l;
		return recArea;
	}
	
	public int areaofcircle(int r) {
		int circArea = (int) (Math.PI * (Math.pow(r, 2)));
		return Math.round(circArea);
	}
	
	public int shapeselector(char shape, int a, int b) {
		
		int selection = 0;
		
		if (shape == 't') {
			selection = areaoftriangle(a, b);
		} else if (shape == 'r') {
			selection = areaofrectangle(a, b);
		} else if (shape == 's') {
			selection = areaofsquare(a);
		} else {
			selection = areaofcircle(a);
		}
		
		return selection;
	}
}

package practical;

public class Task2 {
		
		public String sort(int a, int b) {
			
			if (a < b) {
				return a + ", " + b;
				
			} else if (a > b) {
				return b + ", " + a;
				
			} else {
				return "" + a;
			}
		}

	}
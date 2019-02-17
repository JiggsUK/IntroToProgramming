package practical;

public class Task7Columns {

	public static void main(String[] args) {
		System.out.println(" ----------------------");
		for (int a = 1; a< 13; a++) {
			for (int b = 1; b < 13; b++) {
				int calculation = a * b;
				System.out.printf("%-3s %-3s %-3s %-3s %1s %4s %-3s \n", "|", a, "x", b, "=", calculation, "|");
				if (b == 12) {
					System.out.printf("%2s \n", " ----------------------");
				}
			}
		}

	}

}

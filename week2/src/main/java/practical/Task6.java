package practical;

import java.util.Scanner;

public class Task6 {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		
		System.out.println("Enter a positive number");
		
		int num = 0;
		num = user.nextInt();
		
		while (num <= 0) {
			System.out.println("Try again. Enter a positive number");		
			num = user.nextInt();
		}
		
		if (num > 0) {
			int counter = 0;
			while (counter <= 21) {
				double calculation = Math.pow(num, counter);
				System.out.println(num + " to the power of " + counter + " = " + calculation);
				counter ++;
			}
			
		} 
		user.close();
	}
}


package practical;

import java.util.Scanner;


public class Task4 {

	public static void main(String[] args) {
		
		System.out.println("Enter a number");
		Scanner user = new Scanner(System.in);
		
		int largest = Integer.MIN_VALUE;
		int smallest = Integer.MAX_VALUE;
		int num = 0;
		
		do {
			num = user.nextInt();
			if (num < smallest) {
				smallest = num;
			} 
			if (num > largest) {
				largest = num;
			}
		} while (num != -1);
		
		/* user specification issue: 
		 * User does not specify whether to include -1 within the smallest 
		 * number calculation - or use purely as an exit code */
		
		System.out.println("Smallest number recorded was: " + smallest);
		System.out.println("Largest number recorded was: " + largest);
		
		user.close();
	}

}

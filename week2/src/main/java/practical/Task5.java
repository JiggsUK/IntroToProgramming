package practical;

import java.util.Scanner;

public class Task5 {

	public static void main(String[] args) {
		System.out.println("Enter a number");
		Scanner user = new Scanner(System.in);
		
		int num = 0;
		int total = 0;
		
		while (!(num < 0 || total > 1024)) {  // an alternative way could be to use && --'while (num >= 0 && total <= 1024)'
			
			num = user.nextInt();
			total += num;
		}				
		user.close();
	}

}

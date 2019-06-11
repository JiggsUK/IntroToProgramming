package week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Week5 {

	public static void main(String[] args) {
		Week5 testRun = new Week5();
		testRun.task1();
		testRun.task2b(testRun.task2a());
		testRun.fib();
		testRun.task4();
		testRun.task5();
	}

	public void task1() {
		int[] numbers = { 17, 89, 27, 11, 91, 42, 73, 90, 5, 56 };

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		double sum = 0;
		double stdDev = 0;

		for (int element : numbers) {
			if (element < min) {
				min = element;
			}
			else if (element > max) {
				max = element;
			}

			sum += element;
		}

		int range = max - min;
		double mean = sum / (numbers.length);

		for (int element : numbers) {
			stdDev += Math.pow((element - mean), 2);
		}
		double stdDevResult = Math.sqrt(stdDev / numbers.length);

		System.out.println("=== Task 1 ===");
		System.out.println("Minimum number: " + min + "\n" + "Maximum number: " + max);
		System.out.println("Range: " + range);
		System.out.println("Mean: " + mean);
		System.out.printf("Standard Deviation: %.2f \n", stdDevResult);

	}

	public List<Integer> task2a() {
		List<Integer> diceSum = new ArrayList<Integer>();
		for (int a = 1; a <= 6; a++) {
			for (int b = 1; b <= 6; b++) {
				int sum = a + b;
				diceSum.add(sum);
			}
		}
		System.out.println("\n=== Task 2 ===");
		System.out.println("Sum of values on 2 dice: " + Arrays.toString(diceSum.toArray()).replace("[", "").replace("]", ""));
		return diceSum;
	}

	public List<Integer> task2b(List<Integer> list) {
		List<Integer> uniqueVal = new ArrayList<Integer>();
		uniqueVal = list.stream().distinct().collect(Collectors.toList()); // TODO look at using a HashMap as alternative
		System.out.println("Unique values: " + Arrays.toString(uniqueVal.toArray()).replace("[", "").replace("]", ""));
		return uniqueVal;
	}

	public int[] fib() {
		int[] fibArray = new int[20];
		fibArray[0] = 0;
		fibArray[1] = 1; //initialise first 2 numbers ready for loop

		for (int count = 2; count <= 19; count++) {
			fibArray[count] = ((fibArray[count - 2]) + (fibArray[count - 1]));
		}
		System.out.println("\n=== Task 3 ===");
		System.out.println("First 20 fibonacci numbers: " + Arrays.toString(fibArray).replace("[", "").replace("]", ""));
		return fibArray;
	}

	public int[][] task4() {
		int[][] matrix = new int[2][3];

		// create array
		int count = 1;
		while (count < 7) {
			for (int a = 0; a <= 1; a++) {
				for (int b = 0; b <= 2; b++) {
					matrix[a][b] = count;
					count++;
				}
			}
		}
		System.out.println("\n=== Task 4 ===");
		System.out.println("Original Matrix:\n" + Arrays.deepToString(matrix).replace(" ", "").replace("[", "")
				.replace("],", "\n").replace(",", " ").replace("]", ""));

		// transpose array
		int[][] transposedMatrix = new int[3][2];
		for (int a = 0; a <= 1; a++) {
			for (int b = 0; b <= 2; b++) {
				transposedMatrix[b][a] = matrix[a][b];
			}
		}
		System.out.println("Transposed matrix:\n" + Arrays.deepToString(transposedMatrix).replace(" ", "")
				.replace("[", "").replace("],", "\n").replace(",", " ").replace("]", ""));
		return transposedMatrix;
	}

	public int[][] task5() {
		int[][] matrix1 = { { 2, 3, 4 }, { 1, 0, 1 }, { 4, 3, 2 } };
		int[][] matrix2 = { { 5, 3, 5 }, { 1, 0, 1 }, { 3, 2, 3 } };
		int[][] resultMatrix = new int[3][3];
		for (int a = 0; a <= 2; a++) {
			for (int b = 0; b <= 2; b++) {
				for (int c = 0; c <= 2; c++) {
					resultMatrix[a][b] += matrix1[a][c] * matrix2[c][b];
				}
			}
		}
		System.out.println("\n=== Task 5 ===");
		System.out.println("Matrix 1:\n" + Arrays.deepToString(matrix1).replace(" ", "").replace("[", "")
				.replace("],", "\n").replace(",", " ").replace("]", ""));
		System.out.println("\nMatrix 2:\n" + Arrays.deepToString(matrix2).replace(" ", "").replace("[", "")
				.replace("],", "\n").replace(",", " ").replace("]", ""));
		System.out.printf("%s %s\n %3s\n %s", "\nMatrix Result:\n",
				Arrays.toString(resultMatrix[0]).replace(" ", "").replace("[", "").replace("],", "\n").replace(",", " ")
						.replace("]", ""),
				Arrays.toString(resultMatrix[1]).replace(" ", "").replace("[", "").replace("],", "\n")
						.replace(",", "  ").replace("]", ""),
				Arrays.toString(resultMatrix[2]).replace(" ", "").replace("[", "").replace("],", "\n").replace(",", " ")
						.replace("]", ""));
		return resultMatrix;
	}
}

package week4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task1 {

	public static void main(String[] args) throws IOException {
		String paragraph = new String(Files.readAllBytes(Paths.get("Sherlock Paragraph.txt")));
		System.out.println(paragraph);

	}

}

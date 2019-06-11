package week4;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task2 {

	public static void main(String[] args) throws IOException {
		String paragraph = new String(Files.readAllBytes(Paths.get("Sherlock Paragraph 3")));
		System.out.println("Original Paragraph: \n" + paragraph);

		StringBuilder strippedParagraph = new StringBuilder();

		for (int ind = 0; ind < paragraph.length(); ind++) {
			char letter = paragraph.charAt(ind);
			if (Character.isLetter(letter)) {
				strippedParagraph.append(letter);
			}
		}

		System.out.println("Length of paragraph = " + paragraph.length());
		System.out.println("Stripped Paragraph: \n" + strippedParagraph);

	}

}

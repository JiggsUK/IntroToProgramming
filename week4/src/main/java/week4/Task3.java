package week4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task3 {

	public static void main(String[] args) throws IOException {
		String paragraph = new String(Files.readAllBytes(Paths.get("Sherlock Paragraph 3"))).toUpperCase();
		System.out.println("Original Paragraph: \n" + paragraph);

		StringBuilder strippedParagraph = new StringBuilder();

		for (int ind = 0; ind < paragraph.length(); ind++) {
			char letter = paragraph.charAt(ind);
			if (Character.isLetter(letter)) {
				strippedParagraph.append(letter);
			}
		}
		String finalParagraph = strippedParagraph.toString();
		String encodedParagraph = encodeText(finalParagraph);
		String decodedParagraph = decodeText(encodedParagraph);

		System.out.println("Length of paragraph = " + paragraph.length());
		System.out.println("Stripped Paragraph: \n" + strippedParagraph);
		System.out.println("Encoded Paragraph: \n" + encodedParagraph);
		System.out.println("Decoded Paragraph: \n" + decodedParagraph);
		System.out.println("Enc length: " + encodedParagraph.length());
		System.out.println("Dec length: " + decodedParagraph.length());
	}

	public static String encodeText(String text) {

		StringBuilder encodedText = new StringBuilder();

		for (int ind = 0; ind < text.length(); ind++) {
			int key = 3;
			char letter = text.charAt(ind);
			if (letter == 88 || letter == 89 || letter == 90) {
				letter = (char) (letter - (26 - key));
				encodedText.append(letter);
			} else {
				letter += key;
				encodedText.append(letter);
			}
		}
		return encodedText.toString();
	}

	public static String decodeText(String text) {

		StringBuilder decodedText = new StringBuilder();

		for (int ind = 0; ind < text.length(); ind++) {
			int key = 3;
			char letter = text.charAt(ind);
			if (letter == 65 || letter == 66 || letter == 67) {
				letter = (char) (letter + (26 - key));
				decodedText.append(letter);
			} else {
				letter -= key;
				decodedText.append(letter);
			}
		}
		return decodedText.toString();
	}
}

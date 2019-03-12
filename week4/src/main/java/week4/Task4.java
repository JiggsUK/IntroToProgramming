package week4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task4 {

	public static void main(String[] args) throws IOException {
		String paragraph = new String(Files.readAllBytes(Paths.get("Sherlock Paragraph 3"))).toUpperCase(); //Easier to deal with text in same case
//		String paragraph = "abcdefghijklmnopqrstuvwxyz";  // for testing 
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
		String formattedParagraph = formatEncodedText(encodedParagraph);
		String decodedParagraph = decodeText(formattedParagraph);

//		System.out.println("Length of paragraph = " + finalParagraph.length());
//		System.out.println("Stripped Paragraph: \n" + strippedParagraph);
		System.out.println("Encoded Paragraph: \n" + encodedParagraph);
		System.out.println("Formatted Paragraph: \n" + formattedParagraph);
		System.out.println("Decoded Paragraph: \n" + decodedParagraph);
//		System.out.println("Enc length: " + encodedParagraph.length());
//		System.out.println("Dec length: " + decodedParagraph.length());
	}

	public static String encodeText(String text) {

        StringBuilder encodedText = new StringBuilder();

        for (int ind = 0; ind < text.length(); ind++) {
            int key = 3;
            char letter = text.charAt(ind);
            if (letter == 88 || letter == 89 || letter == 90) {
                letter = (char) (letter - (26 - key));
            } else {
                letter += key;
            }
            encodedText.append(letter);
        }
        return encodedText.toString();
    }

    public static String formatEncodedText(String ciphertext) {

        int start = 0;
		int end = 5;
        int cipherLength = ciphertext.length();
		StringBuilder finaltext = new StringBuilder();

		do {
			String section = ciphertext.substring(start, end);
			finaltext.append(section);
			finaltext.append(" ");
			start += 5;
			end += 5;
		} while (end < cipherLength);

		if (end > cipherLength) {
			int leftoverLetters = cipherLength - (cipherLength % 5);
			int numberOfX = 5 - (cipherLength % 5);
			
			finaltext.append(ciphertext, leftoverLetters, cipherLength);
			
			for (int count = 0; count < numberOfX; count ++) {
				finaltext.append('X');
			}
		}		
		return finaltext.toString();
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
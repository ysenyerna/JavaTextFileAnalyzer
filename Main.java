import java.io.*;
import java.util.Scanner;

public class Main {

	private static char charToCount = ' ';
    public static void main(String[] args) {
		System.out.println("-- Text File Analyzer --\n");

		// Get the character to count
		Scanner inputScanner = new Scanner(System.in);
		System.out.print("Enter the character you would like to count: ");
		charToCount = inputScanner.next().charAt(0);
		inputScanner.close();

		readTextFile("text.txt");

	}

	private static void readTextFile(String filePath) {
		System.out.println("Analyzing TXT file...\n");
		File file = new File(filePath);

		try (Scanner scanner = new Scanner(file)) {

			// Read file info
			System.out.println("File Name: " + file.getName());
			String[] parts = file.getName().split("\\.");
			String ext = parts[parts.length - 1].toUpperCase();
			System.out.println("File Type: " + ext);
			System.out.println("File Size: " + file.length() + " bytes");

			// Read lines
			int lineCount = 0;
			String text = "";
			while (scanner.hasNextLine()) {
				lineCount += 1;
				text = text + scanner.nextLine();
			}
			System.out.println("Line Count: " + lineCount);

			// Count words
			int wordCount = text.trim().split("\\s+").length;
			System.out.println("Word Count: " + wordCount);

			// Count character
			int charCount = 0;
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == charToCount) {
					charCount++;
				}
			}
			System.out.println("Character Count for '" + charToCount + "': " + charCount);
		}
		catch (FileNotFoundException e) {
			System.out.println("An error occured when reading the text file.");

		}
	}
}
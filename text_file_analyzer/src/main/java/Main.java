import java.io.*;
import java.util.Scanner;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Main {

	private static char charToCount = ' ';
    public static void main(String[] args) {
		System.out.println("-- Text File Analyzer --\n");

		// Get the character to count
		Scanner inputScanner = new Scanner(System.in);
		System.out.print("Enter the character you would like to count: ");
		charToCount = inputScanner.next().charAt(0);
		inputScanner.close();
		System.out.println();

		int[] txtResults = readTextFile("text.txt");
		int[] docxResults = readWordFile("word.docx");

		// Print comparison
		System.out.println("Comparison:");

		System.out.println(txtResults[0] == docxResults[0] ? "The file sizes are the same." 
		: (txtResults[0] > docxResults[0] ?  "The TXT file has a larger file size." : "The DOCX file has a larger file size."));

		System.out.println(txtResults[1] == docxResults[1] ? "The line counts are the same." 
		: (txtResults[1] > docxResults[1] ?  "The TXT file has more lines." : "The DOCX file has more lines."));

		System.out.println(txtResults[2] == docxResults[2] ? "The word counts are the same." 
		: (txtResults[2] > docxResults[2] ?  "The TXT file has more words." : "The DOCX file has more words."));

		System.out.println(txtResults[3] == docxResults[3] ? "The character counts are the same." 
		: (txtResults[3] > docxResults[3] ?  "The TXT file has more of the character." : "The DOCX file has more of the character."));
	}

	private static int[] readTextFile(String filePath) {
		int[] results = new int[4];
		System.out.println("Analyzing TXT file...\n");
		File file = new File(filePath);

		try (Scanner scanner = new Scanner(file)) {

			// Read file info
			System.out.println("File Name: " + file.getName());
			String[] parts = file.getName().split("\\.");
			String ext = parts[parts.length - 1].toUpperCase();
			System.out.println("File Type: " + ext);

			// Get file size
			int fileSize = (int)file.length();
			System.out.println("File Size: " + fileSize + " bytes");
			results[0] = fileSize;

			// Read lines
			int lineCount = 0;
			String text = "";
			while (scanner.hasNextLine()) {
				lineCount += 1;
				text = text + scanner.nextLine();
			}
			results[1] = lineCount;
			System.out.println("Line Count: " + lineCount);

			// Count words
			int wordCount = text.trim().split("\\s+").length;
			results[2] = wordCount;
			System.out.println("Word Count: " + wordCount);

			// Count character
			int charCount = 0;
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == charToCount) {
					charCount++;
				}
			}
			results[3] = charCount;
			System.out.println("Character Count for '" + charToCount + "': " + charCount);
		}
		catch (Exception e) {
			System.out.println("An error occured when reading the TXT file.");
			e.printStackTrace();
		}
		System.out.println();
		return results;
	}

	private static int[] readWordFile(String filePath) {
		int[] results = new int[4];
		System.out.println("Analyzing DOCX file...\n");
		File file = new File(filePath);

		try (Scanner scanner = new Scanner(file)) {

			// Read file info
			System.out.println("File Name: " + file.getName());
			String[] parts = file.getName().split("\\.");
			String ext = parts[parts.length - 1].toUpperCase();
			System.out.println("File Type: " + ext);
			int fileSize = (int)file.length();
			results[0] = fileSize;
			System.out.println("File Size: " + fileSize + " bytes");

			// Read contents of the DOCX file using Apache POI
			System.setProperty("log4j2.statusLoggerLevel", "OFF");
			FileInputStream input = new FileInputStream(filePath);
			XWPFDocument document = new XWPFDocument(input);
			XWPFWordExtractor extractor = new XWPFWordExtractor(document);

			String text = extractor.getText();
			extractor.close();

			// Get line count
			int lineCount = text.isEmpty() ? 0 : text.split("\\R").length;
			results[1] = lineCount;
			System.out.println("Line Count: " + lineCount);

			// Get word count
			int wordCount = text.trim().split("\\s+").length;
			results[2] = wordCount;
			System.out.println("Word Count: " + wordCount);

			// Count character
			int charCount = 0;
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == charToCount) {
					charCount++;
				}
			}
			results[3] = charCount;
			System.out.println("Character Count for '" + charToCount + "': " + charCount);

		} catch (Exception e) {
			System.out.println("An error occured when reading the DOCX file.");
			e.printStackTrace();
		}
		System.out.println();
		return results;
    }
}
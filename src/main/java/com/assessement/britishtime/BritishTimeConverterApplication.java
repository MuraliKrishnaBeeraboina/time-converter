package com.assessement.britishtime;

import java.util.Scanner;
import java.util.logging.Logger;
import com.assessement.britishtime.exception.InvalidTimeFormatException;
import com.assessement.britishtime.service.TimeConverter;

//Main application class for the British Time Converter.
public class BritishTimeConverterApplication {
	private static final Logger logger = Logger.getLogger(BritishTimeConverterApplication.class.getName());

	public static void main(String[] args) {
		logger.info("Starting British Time Converter application");
		TimeConverter converter = new TimeConverter();
		Scanner scanner = new Scanner(System.in);

		System.out.println("=== British Time Converter ===");
		System.out.println("Enter time in HH:MM format (e.g., 13:45). Type 'exit' to quit.");

		while (true) {
			System.out.print("Enter time: ");
			String input = scanner.nextLine();
			logger.fine("User input received: " + input);

			if (input.equalsIgnoreCase("exit")) {
				logger.info("User requested exit - shutting down application");
				System.out.println("Exiting application. Goodbye!");
				break;
			}

			try {
				logger.fine("Processing time conversion request for: " + input);
				String spokenForm = converter.convertToBritishSpokenForm(input);
				System.out.println("Spoken form: " + spokenForm);
				logger.info("Successfully processed conversion: " + input + " -> " + spokenForm);
			} catch (InvalidTimeFormatException e) {
				logger.warning("Invalid time format provided by user: " + input + " - " + e.getMessage());
				System.err.println("Error: " + e.getMessage());
			} catch (Exception e) {
				logger.severe("Unexpected error during time conversion: " + e.getMessage());
				System.err.println("An unexpected error occurred: " + e.getMessage());
			}
		}
	}
}

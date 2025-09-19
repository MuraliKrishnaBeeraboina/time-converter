package com.assessement.britishtime.util;

import java.util.logging.Logger;

//Utility class to convert numbers (0-59) into their English word form.
public class NumberToWordConverter {
	private static final Logger logger = Logger.getLogger(NumberToWordConverter.class.getName());

	private static final String[] ONES = { "Zero", "one", "two", "three", "four", "five", "six", "seven", "eight",
			"nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	private static final String[] TENS = { "", "", "twenty", "thirty", "forty", "fifty" };

	// Converts a number (0-59) to its English word representation.
	// @param number the number to convert
	// @return the English word representation of the number
	// @throws IllegalArgumentException if the number is out of the valid range
	// (0-59)
	public static String convert(int number) {
		logger.fine("Converting number to word: " + number);

		if (number < 0 || number > 59) {
			logger.warning("Invalid number for conversion: " + number + " (must be 0-59)");
			throw new IllegalArgumentException("Number must be between 0 and 59.");
		}

		String result;
		if (number < 20) {
			result = ONES[number];
		} else {
			result = TENS[number / 10] + ((number % 10 != 0) ? "-" + ONES[number % 10] : "");
		}

		logger.fine("Number " + number + " converted to: \"" + result + "\"");
		return result;
	}
}
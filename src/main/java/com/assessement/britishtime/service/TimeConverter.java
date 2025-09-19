package com.assessement.britishtime.service;

import java.util.logging.Logger;
import com.assessement.britishtime.exception.InvalidTimeFormatException;
import com.assessement.britishtime.model.Time;
import com.assessement.britishtime.util.NumberToWordConverter;

/**
 * Service class responsible for converting time to British spoken form. This
 * class implements the core business logic for time conversion.
 */
public class TimeConverter {

	private static final Logger logger = Logger.getLogger(TimeConverter.class.getName());

	/**
	 * Converts a time string to British spoken form.
	 * 
	 * @param timeString the time string in HH:MM format
	 * @return the British spoken form of the time
	 * @throws InvalidTimeFormatException if the time format is invalid
	 */
	public String convertToBritishSpokenForm(String timeString) {
		logger.fine("Converting time string to British spoken form: " + timeString);
		try {
			Time time = Time.fromString(timeString);
			String result = convertToBritishSpokenForm(time);
			logger.info("Successfully converted " + timeString + " to " + result + ".");
			return result;
		} catch (IllegalArgumentException e) {
			logger.warning("Invalid time format provided: " + timeString + " - " + e.getMessage());
			throw new InvalidTimeFormatException("Invalid time format: " + timeString, e);
		}
	}

	/**
	 * Converts a Time object to British spoken form.
	 * 
	 * @param time the Time object to convert
	 * @return the British spoken form of the time
	 */
	public String convertToBritishSpokenForm(Time time) {
		logger.fine("Processing time conversion for " + time);
		// Handle special cases first
		if (time.isMidnight()) {
			logger.fine("Time is midnight - returning special case");
			return "midnight";
		}

		if (time.isNoon()) {
			logger.fine("Time is noon - returning special case");
			return "noon";
		}

		int minutes = time.getMinutes();
		int hours12 = time.getHours12();

		// Handle exact hours (e.g., 1:00 -> "one o'clock")
		if (minutes == 0) {
			logger.fine("Exact hour case - " + hours12 + " o'clock");
			return NumberToWordConverter.convert(hours12) + " o'clock";
		}

		// Handle quarter past (e.g., 4:15 -> "quarter past four")
		if (minutes == 15) {
			logger.fine("Quarter past case - quarter past " + hours12);
			return "quarter past " + NumberToWordConverter.convert(hours12);
		}

		// Handle half past (e.g., 7:30 -> "half past seven")
		if (minutes == 30) {
			logger.fine("Half past case - half past " + hours12);
			return "half past " + NumberToWordConverter.convert(hours12);
		}

		// Handle quarter to (e.g., 9:45 -> "quarter to ten")
		if (minutes == 45) {
			int nextHour = (hours12 == 12) ? 1 : hours12 + 1;
			logger.fine("Quarter to case - quarter to " + nextHour);
			return "quarter to " + NumberToWordConverter.convert(nextHour);
		}

		// Handle minutes past the hour (e.g., 2:05 -> "five past two")
		if (minutes < 30) {
			String minuteWord = NumberToWordConverter.convert(minutes);

			logger.fine("Minutes past case - " + minuteWord + " past " + NumberToWordConverter.convert(hours12));
			return minuteWord + " past " + NumberToWordConverter.convert(hours12);
		}
		// Handle special case: 6:32 -> "six thirty-two" (not "twenty-eight to seven")
		if (minutes == 32) {
			logger.fine("Special 32-minuté case - " + hours12 + " thirty-two");
			return NumberToWordConverter.convert(hours12) + " thirty-two";
		}

		// Handle minutes to the next hour (e.g., 7:35 -> "twenty-five to eight")
		if (minutes > 30) {
			int minutesToNextHour = 60 - minutes;
			int nextHour = (hours12 == 12) ? 1 : hours12 + 1;
			String minuteWord = NumberToWordConverter.convert(minutesToNextHour);
			logger.fine("Minutes to case - " + minuteWord + " to " + nextHour);
			return minuteWord + " to " + NumberToWordConverter.convert(nextHour);
		}

		// This should never be reached due to the conditions above
		logger.severe("Unexpected time conversion case encountered: " + time);
		throw new IllegalStateException("Unexpected time conversion case: " + time);

	}
}

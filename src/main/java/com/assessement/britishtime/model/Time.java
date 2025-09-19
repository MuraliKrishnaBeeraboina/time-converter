package com.assessement.britishtime.model;

import java.util.Objects;
import java.util.logging.Logger;
import com.assessement.britishtime.exception.InvalidTimeFormatException;

/**
 * Represents a time with hour and minute components. Provides utility methods
 * for time manipulation and validation.
 */
public class Time {
	private static final Logger logger = Logger.getLogger(Time.class.getName());

	private final int hours;
	private final int minutes;

	/**
	 * Constructs a Time object.
	 * 
	 * @param hours   the hour component (0-23)
	 * @param minutes the minute component (0-59)
	 * @throws IllegalArgumentException if hours or minutes are out of valid range
	 */
	public Time(int hours, int minutes) {
		logger.fine("Creating Time object with hours=" + hours + ", minutes=" + minutes);

		if (hours < 0 || hours > 23) {
			logger.warning("Invalid hours value: " + hours + " (must be 0-23)");
			throw new IllegalArgumentException("Hours must be between 0 and 23.");
		}

		if (minutes < 0 || minutes > 59) {
			logger.warning("Invalid minutes value: " + minutes + " (must be 0-59)");
			throw new IllegalArgumentException("Minutes must be between 0 and 59.");
		}

		this.hours = hours;
		this.minutes = minutes;
		logger.fine("Time object created successfully: " + this);
	}

	/**
	 * Creates a Time object from a string in HH:MM format.
	 * 
	 * @param timeString the time string
	 * @return a Time object
	 * @throws InvalidTimeFormatException if the string format is invalid
	 */

	public static Time fromString(String timeString) {
		logger.fine("Parsing time string: " + timeString);

		if (timeString == null || !timeString.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
			logger.warning("Invalid time string format: " + timeString);
			throw new InvalidTimeFormatException("Time string must be in HH:MM format (e.g., 09:30 or 23:15).");
		}

		String[] parts = timeString.split(":");
		int hours = Integer.parseInt(parts[0]);
		int minutes = Integer.parseInt(parts[1]);

		logger.fine("Successfully parsed time string: " + timeString + " -> " + hours + ":" + minutes);
		return new Time(hours, minutes);
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	/**
	 * Returns the hour in 12-hour format (1-12).
	 * 
	 * @return the 12-hour format hour
	 */
	public int getHours12() {
		if (hours == 0)
			return 12; // 00:xx is 12 AM
		else if (hours > 12) {
			return hours - 12;
		}
		return hours;
	}

// Checks if the time is midnight (00:00).

	/**
	 * @return true if it's midnight, false otherwise
	 */
	public boolean isMidnight() {
		return hours == 0 && minutes == 0;
	}

	/**
	 * Checks if the time is noon (12:00).
	 * 
	 * @return true if it's noon, false otherwise
	 */
	public boolean isNoon() {
		return hours == 12 && minutes == 0;
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d", hours, minutes);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Time time = (Time) o;
		return hours == time.hours && minutes == time.minutes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hours, minutes);
	}
}

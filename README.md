# time-converter
British Time Converter

A Java application that converts time input (HH:MM format) to British spoken form, implementing all the requirements from the coding challenge.

Features
- Converts all time patterns to British spoken form
- Handles special cases (midnight, noon)
- Supports exact hours, minutes past/to, quarters, and halves
- Handles the special "6:32" case correctly
- Comprehensive input validation and error handling
- Clean, readable code with proper documentation

Requirements
- Java 11 or higher
- Maven 3.6 or higher

Project Structure

src/main/java/com/assessment/britishtime/
├── BritishTimeConverterApplication.java # Main application
├── model/
│   └── Time.java # Immutable time value object
├── service/
│   └── TimeConverter.java # Core conversion logic
├── exception/
│   └── InvalidTimeFormatException.java # Custom exception
└── util/
└── NumberToWordConverter.java # Number to word conversion

How to Run:

Using Maven
1. Interactive Mode: bash mvn exec:java
2. Command Line with Arguments: bash mvn exec:java -Dexec.args="12:00"

Using Java Directly
1. Compile the project: bash mvn compile
2. Run the application: bash java -cp target/classes com.assessment.britishtime.BritishTimeConverterApplication
3. Run with specific time: bash java -cp target/classes com.assessment.britishtime.BritishTimeConverterApplication "12:00"

Examples
| Input | Output | ---------- | 1:00 | one o'clock | 2:05 | five past two | 3:10 | ten past three | 4:15 | quarter past four | 5:20 | twenty past five | 6:25 | twenty-five past six | 6:32 | six thirty-two | 7:30 | half past seven | 7:35 | twenty-five to eight | 8:40 | twenty to nine | 9:45 | quarter to ten | 10:50 | ten to eleven | 11:55 | five to twelve | 00:00 | midnight | 12:00 | noon |

Design Patterns Used
1. Value Object Pattern: Immutable Time class
2. Service Pattern: TimeConverter for business logic
3. Factory Method: Time.fromString() for object creation
4. Exception Handling: Custom exceptions with meaningful messages

Code Quality
- Clean, readable code with proper naming conventions
- Comprehensive JavaDoc documentation
- Object-oriented design with proper separation of concerns
- Immutable value objects
- Service layer pattern for business logic
- Utility classes for reusable functionality
- Custom exception handling

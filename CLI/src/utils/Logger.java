package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

// oop concepts used:
// 1. Inheritance: The Logger class extends the Singleton class to implement the Singleton design pattern.
// 2. Encapsulation: The Logger class encapsulates the log method to log messages to the console.
// 3. Polymorphism: The Logger class implements the Singleton design pattern using polymorphism.
// 4. Abstraction: The Logger class abstracts the logging functionality from the rest of the system.

public class Logger {
    private static Logger instance;
    private static PrintWriter writer;

    // Private constructor to prevent instantiation
    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter("log.txt", true)); // Append mode
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize logger", e);
        }
    }

    // Singleton pattern to ensure only one instance of Logger
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Method to log messages to the console and file
    public static synchronized void log(String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String logMessage = timestamp + " - " + message;

        // Log to console
        System.out.println(logMessage);

        // Log to file
        writer.println(logMessage);
        writer.flush();
    }

    // Close the writer (called when the program is exiting)
    public synchronized void close() {
        writer.close();
    }
}

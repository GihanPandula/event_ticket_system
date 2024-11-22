package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance;
    private final PrintWriter writer;

    private Logger() throws IOException {
        writer = new PrintWriter(new FileWriter("log.txt", true), true);
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            try {
                instance = new Logger();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public synchronized void log(String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        writer.println(timestamp + " - " + message);
    }
}
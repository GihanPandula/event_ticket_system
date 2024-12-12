import config.Configiguration;
import customer.Customer;
import ticket.TicketPool;
import vendor.Vendor;
import utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// oop concepts used:
// 1. Inheritance: The Logger class extends the Singleton class to implement the Singleton design pattern.
// 2. Encapsulation: The Logger class encapsulates the log method to log messages to the console.
// 3. Polymorphism: The Logger class implements the Singleton design pattern using polymorphism.
// 4. Abstraction: The Logger class abstracts the logging functionality from the rest of the system.

// Main class to run the program and manage the system
public class Main {
    public static void main(String[] args) {
        Configiguration config = new Configiguration();
        config.load();

        // Create ticket pool and logger
        TicketPool ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());
        Logger logger = Logger.getInstance();

        // Create vendor thread with release rate and vendor ID
        List<Thread> vendors = new ArrayList<>();
        for (int i = 0; i < config.getVendorCount(); i++) {
            vendors.add(new Thread(new Vendor(ticketPool, config.getTicketReleaseRate(), i + 1)));
        }

        // Create customer threads with retrieval rate and customer ID
        List<Thread> customers = new ArrayList<>();
        for (int i = 0; i < config.getCustomerCount(); i++) {
            customers.add(new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), i + 1)));
        }

        // Start the system
        Scanner scanner = new Scanner(System.in);
        boolean running = false;

        // Monitor thread to check if all tickets are sold
        Thread monitorThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (ticketPool.allTicketsSold()) { // Check if all tickets are sold
                    vendors.forEach(Thread::interrupt); // Interrupt all vendor threads
                    customers.forEach(Thread::interrupt); // Interrupt all customer threads
                    Logger.log("All tickets sold. System stopped."); // Log message
                    break;
                }
                try {
                    Thread.sleep(1000); // Check every second
                } catch (InterruptedException e) { // Handle exception
                    Thread.currentThread().interrupt(); // Interrupt thread
                }
            }
        });

        // Run the system
        while (true) {
            System.out.println("Enter command: [1: start | 2: stop | 3: exit]"); // Display options
            String command = scanner.nextLine().toLowerCase(); // Read user input

            // Check user input
            switch (command) {
                case "1":
                    if (!running) {
                        vendors.forEach(Thread::start);
                        customers.forEach(Thread::start);
                        monitorThread.start();
                        running = true;
                        Logger.log("System started.");
                    } else {
                        Logger.log("System is already running.");
                    }
                    break;

                case "2":
                    if (running) {
                        vendors.forEach(Thread::interrupt);
                        customers.forEach(Thread::interrupt);
                        monitorThread.interrupt();
                        running = false;
                        Logger.log("System stopped.");
                    } else {
                        Logger.log("System is not running.");
                    }
                    break;

                case "3":
                    if (running) {
                        vendors.forEach(Thread::interrupt);
                        customers.forEach(Thread::interrupt);
                        monitorThread.interrupt();
                    }
                    Logger.log("Exiting program.");
                    return;
                default:
                    Logger.log("Invalid command.");
            }
        }
    }
}
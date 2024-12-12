package config;

import java.util.Scanner;

// oop concepts used:
// Encapsulation: The Configiguration class encapsulates the configuration values for the system.
// Abstraction: The Configiguration class abstracts the process of loading configuration values from the user.
// Encapsulation: The Configiguration class encapsulates the configuration values and provides getters for access.

public class Configiguration {
    private int totalTickets; // Total number of tickets available for sale
    private int ticketReleaseRate; // Number of tickets released by each vendor per cycle
    private int customerRetrievalRate; // Number of tickets retrieved by each customer per cycle
    private int maxTicketCapacity; // Maximum number of tickets that can be held by the pool
    private int vendorCount; // Number of vendors in the system
    private int customerCount; // Number of customers in the system

    public void load() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        // Prompt user for configuration values and validate input using helper method
        totalTickets = promptForInt(scanner, "Enter Total Tickets (totalTickets): ", 1, Integer.MAX_VALUE);
        ticketReleaseRate = promptForInt(scanner, "Enter Ticket Release Rate (ticketReleaseRate): ", 1, totalTickets);
        customerRetrievalRate = promptForInt(scanner, "Enter Customer Retrieval Rate (customerRetrievalRate): ", 1, totalTickets);
        maxTicketCapacity = promptForInt(scanner, "Enter Max Ticket Capacity (maxTicketCapacity): ", 1, totalTickets);
        vendorCount = promptForInt(scanner, "Enter Number of Vendors: ", 1, 100);
        customerCount = promptForInt(scanner, "Enter Number of Customers: ", 1, 100);
    }

    // Helper method to prompt user for integer input within a specified range
    private int promptForInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) { // Loop until valid input is provided
            System.out.print(prompt); // Display prompt
            if (scanner.hasNextInt()) { // Check if input is an integer
                value = scanner.nextInt(); // Read integer value
                if (value >= min && value <= max) { // Validate range
                    return value; // Return valid input
                }
            }
            scanner.nextLine(); // Clear invalid input
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + "."); // Display error message
        }
    }

    // Getters for all fields
    public int getTotalTickets() { return totalTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }
    public int getVendorCount() { return vendorCount; }
    public int getCustomerCount() { return customerCount; }
}

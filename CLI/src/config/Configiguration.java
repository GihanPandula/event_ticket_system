package config;

import java.util.Scanner;

public class Configiguration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int vendorCount;
    private int customerCount;

    public void load() {
        Scanner scanner = new Scanner(System.in);

        totalTickets = promptForInt(scanner, "Enter Total Tickets (totalTickets): ", 1, Integer.MAX_VALUE);
        ticketReleaseRate = promptForInt(scanner, "Enter Ticket Release Rate (ticketReleaseRate): ", 1, totalTickets);
        customerRetrievalRate = promptForInt(scanner, "Enter Customer Retrieval Rate (customerRetrievalRate): ", 1, totalTickets);
        maxTicketCapacity = promptForInt(scanner, "Enter Max Ticket Capacity (maxTicketCapacity): ", 1, totalTickets);
        vendorCount = promptForInt(scanner, "Enter Number of Vendors: ", 1, 100);
        customerCount = promptForInt(scanner, "Enter Number of Customers: ", 1, 100);
    }

    private int promptForInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                }
            }
            scanner.nextLine(); // Clear invalid input
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
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

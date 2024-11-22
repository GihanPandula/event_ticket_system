import com.google.gson.Gson;
import config.Configuration;
import customer.Customer;
import ticket.TicketPool;
import vendor.Vendor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static boolean systemRunning = false;
    private static List<Thread> vendorThreads;
    private static List<Thread> customerThreads;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();

        while (true) {
            System.out.println("1. Start System");
            System.out.println("2. Stop System");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    if (!systemRunning) {
                        startSystem(scanner, gson);
                    } else {
                        System.out.println("System is already running.");
                    }
                    break;
                case 2:
                    if (systemRunning) {
                        stopSystem();
                    } else {
                        System.out.println("System is not running.");
                    }
                    break;
                case 3:
                    if (systemRunning) {
                        stopSystem();
                    }
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void startSystem(Scanner scanner, Gson gson) {
        int totalTickets = getValidatedInput(scanner, "Enter total number of tickets: ");
        int maxTicketCapacity = getValidatedInput(scanner, "Enter maximum ticket capacity: ");
        int ticketReleaseRate = getValidatedInput(scanner, "Enter ticket release rate (seconds): ") * 1000;
        int customerRetrievalRate = getValidatedInput(scanner, "Enter customer retrieval rate (seconds): ") * 1000;
        int vendorCount = getValidatedInput(scanner, "Enter the number of vendors: ");
        int customerCount = getValidatedInput(scanner, "Enter the number of customers: ");

        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, vendorCount, customerCount);
        saveConfiguration(gson, config);

        TicketPool ticketPool = new TicketPool(totalTickets, maxTicketCapacity);
        vendorThreads = new ArrayList<>();
        customerThreads = new ArrayList<>();

        for (int i = 0; i < vendorCount; i++) {
            Thread vendorThread = new Thread(new Vendor(ticketPool, ticketReleaseRate, i + 1));
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        for (int i = 0; i < customerCount; i++) {
            Thread customerThread = new Thread(new Customer(ticketPool, customerRetrievalRate, i + 1));
            customerThreads.add(customerThread);
            customerThread.start();
        }

        systemRunning = true;
        new Thread(Main::monitorSystem).start();

        while (systemRunning) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void stopSystem() {
        System.out.println("Stopping the system...");
        systemRunning = false;

        for (Thread vendor : vendorThreads) {
            vendor.interrupt();
        }

        for (Thread customer : customerThreads) {
            customer.interrupt();
        }

        try {
            for (Thread vendor : vendorThreads) {
                vendor.join();
            }
            for (Thread customer : customerThreads) {
                customer.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Error stopping threads: " + e.getMessage());
        }
        System.out.println("System stopped successfully.");
    }

    private static void monitorSystem() {
        while (systemRunning) {
            boolean allThreadsFinished = vendorThreads.stream().allMatch(thread -> !thread.isAlive()) &&
                    customerThreads.stream().allMatch(thread -> !thread.isAlive());

            if (allThreadsFinished) {
                System.out.println("All threads have finished. Stopping system...");
                stopSystem();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static int getValidatedInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void saveConfiguration(Gson gson, Configuration config) {
        try (FileWriter writer = new FileWriter("config.json")) {
            gson.toJson(config, writer);
            System.out.println("\nConfiguration saved to 'config.json'.\n");
        } catch (IOException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
        }
    }
}
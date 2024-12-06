import config.Configiguration;
import customer.Customer;
import ticket.TicketPool;
import vendor.Vendor;
import utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Configiguration config = new Configiguration();
        config.load();

        TicketPool ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());
        Logger logger = Logger.getInstance();

        List<Thread> vendors = new ArrayList<>();
        for (int i = 0; i < config.getVendorCount(); i++) {
            vendors.add(new Thread(new Vendor(ticketPool, config.getTicketReleaseRate(), i + 1)));
        }

        List<Thread> customers = new ArrayList<>();
        for (int i = 0; i < config.getCustomerCount(); i++) {
            customers.add(new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), i + 1)));
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = false;

        while (true) {
            System.out.println("Enter command: [start | stop | exit]");
            String command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "start":
                    if (!running) {
                        vendors.forEach(Thread::start);
                        customers.forEach(Thread::start);
                        running = true;
                        Logger.log("System started.");
                    } else {
                        Logger.log("System is already running.");
                    }
                    break;

                case "stop":
                    if (running) {
                        vendors.forEach(Thread::interrupt);
                        customers.forEach(Thread::interrupt);
                        running = false;
                        Logger.log("System stopped.");
                    } else {
                        Logger.log("System is not running.");
                    }
                    break;

                case "exit":
                    if (running) {
                        vendors.forEach(Thread::interrupt);
                        customers.forEach(Thread::interrupt);
                    }
                    Logger.log("Exiting program.");
                    return;

                default:
                    Logger.log("Invalid command.");
            }
        }
    }
}

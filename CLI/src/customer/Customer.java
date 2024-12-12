package customer;

import ticket.TicketPool;
import utils.Logger;

// oop concepts used:
// Encapsulation: The TicketPool object is encapsulated within the Customer class.
// Inheritance: The Customer class implements the Runnable interface.
// Polymorphism: The run method is overridden in the Customer class.
// Abstraction: The run method abstracts the behavior of a customer purchasing tickets.

public class Customer implements Runnable {
    private final TicketPool ticketPool;  // TicketPool object
    private final int retrievalRate;    // Retrieval rate
    private final int customerId;     // Customer ID

    public Customer(TicketPool ticketPool, int retrievalRate, int customerId) { // Constructor
        this.ticketPool = ticketPool; // Initialize ticketPool
        this.retrievalRate = retrievalRate; // Initialize retrievalRate
        this.customerId = customerId; // Initialize customerId
    }

    @Override
    public void run() { // Run method
        while (!Thread.currentThread().isInterrupted() && !ticketPool.allTicketsSold()) {  // Check if thread is not interrupted and all tickets are not sold
            for (int i = 0; i < retrievalRate; i++) {  // Loop through retrieval rate
                Integer ticket = ticketPool.retrieveTicket();  // Retrieve ticket
                if (ticket != null) {  // Check if ticket is not null
                    Logger.log("Customer " + customerId + " purchased ticket #" + ticket);
                } else {
                    Logger.log("Customer " + customerId + " found no tickets available.");
                }
            }
            try {
                Thread.sleep(1000); // Simulate time delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}


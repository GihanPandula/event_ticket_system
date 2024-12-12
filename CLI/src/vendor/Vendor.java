package vendor;

import ticket.TicketPool;
import utils.Logger;

// oop concepts used:
// Encapsulation: The Vendor class encapsulates the ticket pool and release rate, and vendor ID.
// Inheritance: The Vendor class implements the Runnable interface.
// Polymorphism: The run method is overridden to define the behavior of the vendor thread.
// Abstraction: The Vendor class abstracts the behavior of a vendor in the ticket system.

public class Vendor implements Runnable {
    private final TicketPool ticketPool; // Ticket pool reference
    private final int releaseRate; // Ticket release rate
    private final int vendorId;     // Vendor ID

    // Constructor
    public Vendor(TicketPool ticketPool, int releaseRate, int vendorId) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.vendorId = vendorId;
    }

    @Override
    public void run() { // Run method for vendor thread
        int ticketId = 1; // Initialize ticket ID
        while (!Thread.currentThread().isInterrupted() && !ticketPool.allTicketsSold()) {  // Check if thread is interrupted or all tickets are sold
            for (int i = 0; i < releaseRate; i++) {  // Release tickets based on release rate
                if (ticketPool.addTicket(ticketId)) {  // Add ticket to pool
                    Logger.log("Vendor " + vendorId + " added ticket #" + ticketId);  // Log ticket addition
                    ticketId++;  // Increment ticket ID
                } else {  // If ticket pool is full or limit reached
                    Logger.log("Vendor " + vendorId + " could not add ticket (Pool full or limit reached).");  // Log failure
                }
            }
            try {
                Thread.sleep(1000); // Simulate time delay
            } catch (InterruptedException e) {  // Handle exception
                Thread.currentThread().interrupt();  // Interrupt thread
            }
        }
    }

}

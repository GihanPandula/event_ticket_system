package vendor;

import ticket.TicketPool;
import utils.Logger;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int vendorId;
    private final Logger logger = Logger.getInstance();

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int vendorId) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        try {
            while (ticketPool.canAddMoreTickets()) {
                if (ticketPool.addTicket(vendorId)) {
                    logger.log("Vendor " + vendorId + " added a ticket.");
                    Thread.sleep(ticketReleaseRate);
                } else {
                    logger.log("Vendor " + vendorId + " cannot add tickets. Ticket pool is full. Waiting for customers to buy tickets.");
                    Thread.sleep(1000); // Wait before trying again
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
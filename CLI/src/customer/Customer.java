package customer;

import ticket.TicketPool;
import utils.Logger;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final int customerId;
    private final Logger logger = Logger.getInstance();

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int customerId) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.customerId = customerId;
    }

    @Override
    public void run() {
        try {
            while (!ticketPool.allTicketsSold() || ticketPool.getRemainingTickets() > 0) {
                if (ticketPool.retrieveTicket(customerId)) {
                    logger.log("Customer " + customerId + " bought a ticket.");
                    Thread.sleep(customerRetrievalRate);
                } else {
                    logger.log("Customer " + customerId + " cannot buy tickets. No tickets left. Waiting for vendors to release tickets.");
                    Thread.sleep(1000); // Wait before trying again
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
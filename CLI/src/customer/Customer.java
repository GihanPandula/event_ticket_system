package customer;

import ticket.TicketPool;
import utils.Logger;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRate;
    private final int customerId;

    public Customer(TicketPool ticketPool, int retrievalRate, int customerId) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
        this.customerId = customerId;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && !ticketPool.allTicketsSold()) {
            for (int i = 0; i < retrievalRate; i++) {
                Integer ticket = ticketPool.retrieveTicket();
                if (ticket != null) {
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


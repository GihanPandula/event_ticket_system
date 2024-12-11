package vendor;

import ticket.TicketPool;
import utils.Logger;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;
    private final int vendorId;

    public Vendor(TicketPool ticketPool, int releaseRate, int vendorId) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.vendorId = vendorId;
    }

//    @Override
//    public void run() {
//        int ticketId = 1;
//        while (!Thread.currentThread().isInterrupted()) {
//            for (int i = 0; i < releaseRate; i++) {
//                if (ticketPool.addTicket(ticketId)) {
//                    Logger.log("Vendor " + vendorId + " added ticket #" + ticketId);
//                    ticketId++;
//                } else {
//                    Logger.log("Vendor " + vendorId + " could not add ticket (Pool full or limit reached).");
//                }
//            }
//            try {
//                Thread.sleep(1000); // Simulate time delay
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//    }

    @Override
    public void run() {
        int ticketId = 1;
        while (!Thread.currentThread().isInterrupted() && !ticketPool.allTicketsSold()) {
            for (int i = 0; i < releaseRate; i++) {
                if (ticketPool.addTicket(ticketId)) {
                    Logger.log("Vendor " + vendorId + " added ticket #" + ticketId);
                    ticketId++;
                } else {
                    Logger.log("Vendor " + vendorId + " could not add ticket (Pool full or limit reached).");
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

package ticket;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final int totalTickets;
    private final int maxTicketCapacity;
    private final Queue<String> tickets;
    private int totalTicketsAdded;
    private int totalTicketsSold;

    public TicketPool(int totalTickets, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.maxTicketCapacity = maxTicketCapacity;
        this.tickets = new LinkedList<>();
        this.totalTicketsAdded = 0;
        this.totalTicketsSold = 0;
    }

    public synchronized boolean addTicket(int vendorId) {
        if (totalTicketsAdded >= totalTickets) {
            return false;
        }
        while (tickets.size() >= maxTicketCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        if (totalTicketsAdded < totalTickets) {
            tickets.add("Ticket " + (totalTicketsAdded + 1));
            totalTicketsAdded++;
            System.out.println("Vendor " + vendorId + " added 1 ticket. Total added: " + totalTicketsAdded);
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized boolean retrieveTicket(int customerId) {
        while (tickets.isEmpty() && totalTicketsAdded > totalTicketsSold) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        if (tickets.isEmpty()) {
            return false;
        }
        tickets.poll();
        totalTicketsSold++;
        System.out.println("Customer " + customerId + " retrieved a ticket. Tickets remaining: " + (totalTickets - totalTicketsSold));
        notifyAll();
        return true;
    }

    public synchronized boolean allTicketsSold() {
        return totalTicketsSold >= totalTickets;
    }

    public synchronized boolean canAddMoreTickets() {
        return totalTicketsAdded < totalTickets;
    }

    public synchronized int getRemainingTickets() {
        return tickets.size();
    }
}
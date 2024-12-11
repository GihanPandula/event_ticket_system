package ticket;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<>();
    private final int maxCapacity;
    private final int totalTickets;
    private int ticketsSold = 0;
    private final Lock lock = new ReentrantLock();

    public TicketPool(int totalTickets, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
    }

    public boolean addTicket(int ticketId) {
        lock.lock();
        try {
            if (tickets.size() < maxCapacity && ticketsSold + tickets.size() < totalTickets) {
                tickets.add(ticketId);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public Integer retrieveTicket() {
        lock.lock();
        try {
            if (!tickets.isEmpty()) {
                ticketsSold++;
                return tickets.poll();
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    public boolean allTicketsSold() {
        lock.lock();
        try {
            return ticketsSold == totalTickets;
        } finally {
            lock.unlock();
        }
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public int getAvailableTickets() {
        return tickets.size();
    }
}


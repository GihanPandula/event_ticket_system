package ticket;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// oop concepts use:
// 1. Encapsulation: The TicketPool class encapsulates the ticket pool data structure and provides methods to add, retrieve, and check the status of tickets.
// 2. Inheritance: The TicketPool class does not inherit from any other class.
// 3. Polymorphism: The TicketPool class does not demonstrate polymorphism.
// 4. Abstraction: The TicketPool class abstracts the ticket pool data structure and provides methods to interact with it.

public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<>(); // Queue to store ticket IDs
    private final int maxCapacity; // Maximum capacity of the ticket pool
    private final int totalTickets; // Total number of tickets
    private int ticketsSold = 0; // Number of tickets sold
    private final Lock lock = new ReentrantLock(); // Lock to synchronize access to the ticket pool

    public TicketPool(int totalTickets, int maxCapacity) {  // Constructor
        this.totalTickets = totalTickets;  // Initialize totalTickets
        this.maxCapacity = maxCapacity;  // Initialize maxCapacity
    }

    public boolean addTicket(int ticketId) {  // Method to add a ticket to the pool
        lock.lock(); // Acquire the lock
        // Check if the ticket pool is not full and the total number of tickets has not been reached
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

    // Method to retrieve a ticket from the pool
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

    // Method to check if all tickets are sold
    public boolean allTicketsSold() {
        lock.lock();
        try {
            return ticketsSold == totalTickets;
        } finally {
            lock.unlock();
        }
    }

    // Getters for ticketsSold and availableTickets
    public int getTicketsSold() {
        return ticketsSold;
    }

    // Method to get the number of available tickets
    public int getAvailableTickets() {
        return tickets.size();
    }
}


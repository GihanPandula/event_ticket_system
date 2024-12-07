package com.oop.event_ticket_system.domain;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int totalTickets;
    private int currentTickets;
    private int maxCapacity;

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public TicketPool(int totalTickets, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
        this.currentTickets = 0;
    }

    public void addTickets(int count) throws InterruptedException {
        lock.lock();
        try {
            while (currentTickets + count > maxCapacity) {
                notFull.await();
            }
            currentTickets += count;
            System.out.println("Added tickets: " + count + ", Current: " + currentTickets);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void retrieveTickets(int count) throws InterruptedException {
        lock.lock();
        try {
            while (currentTickets < count) {
                notEmpty.await();
            }
            currentTickets -= count;
            System.out.println("Retrieved tickets: " + count + ", Current: " + currentTickets);
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int getCurrentTickets() {
        lock.lock();
        try {
            return currentTickets;
        } finally {
            lock.unlock();
        }
    }

    public int getTotalTickets() {
        return 0;
    }

    public int getMaxTicketCapacity() {
        return 0;
    }

    public int getTicketReleaseRate() {
        return 0;
    }

    public int getCustomerRetrievalRate() {
        return 0;
    }
}

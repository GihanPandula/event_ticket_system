package com.oop.event_ticket_system.exception;

// Custom exception for ticket purchase
public class TicketPurchaseException extends RuntimeException {
    public TicketPurchaseException(String message) {
        super(message);
    }
}

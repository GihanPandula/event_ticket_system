package com.oop.event_ticket_system.exception;

// This exception is thrown when the ticket config is not found
public class TicketConfigNotFoundException extends RuntimeException {
    public TicketConfigNotFoundException(String message) {
        super(message);
    }
}

package com.oop.event_ticket_system.dto;

public class TicketConfigRequest { // DTO for ticket configuration request
    private int totalTickets;
    private int maxTicketCapacity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;

    // Getters and Setters
    public int getTotalTickets() {
        return totalTickets;    // Get total tickets
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets; // Set total tickets
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;   // Get max ticket capacity
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity; // Set max ticket capacity
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate; // Get ticket release rate
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate; // Set ticket release rate
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate; // Get customer retrieval rate
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate; // Set customer retrieval rate
    }
}

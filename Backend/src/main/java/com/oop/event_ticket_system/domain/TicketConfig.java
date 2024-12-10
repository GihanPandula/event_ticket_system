package com.oop.event_ticket_system.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_config")
public class TicketConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
    private Long id;

    private int totalTickets; // Total number of tickets available
    private int maxTicketCapacity; // Maximum number of tickets that can be released at a time
    private int ticketReleaseRate; // Number of tickets released per second
    private int customerRetrievalRate; // Number of tickets a customer can retrieve per second

    // Status of the ticket configuration
    @Column(nullable = false)
    private String status = "active"; // Defaults to "active" status when created

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

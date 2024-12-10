package com.oop.event_ticket_system.service;

import com.oop.event_ticket_system.domain.TicketConfig;
import com.oop.event_ticket_system.exception.TicketConfigNotFoundException;
import com.oop.event_ticket_system.exception.TicketPurchaseException;
import com.oop.event_ticket_system.repository.TicketConfigRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    // TicketConfigRepository is an interface that extends JpaRepository
    private TicketConfigRepository configRepository;

    // Active ticket pool configuration
    private TicketConfig activeTicketConfig;

    // Get the active ticket pool configuration
    public void initializeOrUpdatePool(TicketConfig newConfig) {
        Optional<TicketConfig> existingConfig = configRepository.findByStatus("active");

        // If an active ticket pool already exists, update it with the new configuration values
        if (existingConfig.isPresent()) {
            TicketConfig config = existingConfig.get();
            if (config.getTotalTickets() > 0) {
                config.setTotalTickets(newConfig.getTotalTickets());
                config.setMaxTicketCapacity(newConfig.getMaxTicketCapacity());
                config.setTicketReleaseRate(newConfig.getTicketReleaseRate());
                config.setCustomerRetrievalRate(newConfig.getCustomerRetrievalRate());
                configRepository.save(config);
                activeTicketConfig = config;
            } else { // If the existing pool has no tickets, replace it with the new configuration
                config.setStatus("inactive");
                configRepository.save(config);

                newConfig.setStatus("active");
                configRepository.save(newConfig);
                activeTicketConfig = newConfig;
            }
        } else { // If no active ticket pool exists, create a new one
            newConfig.setStatus("active");
            configRepository.save(newConfig);
            activeTicketConfig = newConfig;
        }
    }

    @Transactional
    // Reset the active ticket pool configuration
    public void resetActiveTicketPool() {
        Optional<TicketConfig> existingConfig = configRepository.findByStatus("active");

        // If an active ticket pool exists, delete it
        if (existingConfig.isPresent()) {
            TicketConfig oldConfig = existingConfig.get();

            // Delete the active configuration from the database
            configRepository.delete(oldConfig);

            // Clear the in-memory reference to the active configuration
            activeTicketConfig = null;
        } else {
            throw new TicketConfigNotFoundException("No active ticket pool found to reset.");
        }
    }

    // Get the total number of tickets in the active ticket pool
    public int getCurrentTickets() {
        return activeTicketConfig != null ? activeTicketConfig.getTotalTickets() : 0;
    }

    // Get the maximum ticket capacity of the active ticket pool
    public void addTickets(int count) {
        if (activeTicketConfig == null || "inactive".equals(activeTicketConfig.getStatus())) {
            throw new TicketPurchaseException("Cannot add tickets to an inactive or non-existent ticket pool.");
        }

        // Check if the total number of tickets exceeds the maximum capacity
        activeTicketConfig.setTotalTickets(activeTicketConfig.getTotalTickets() + count);
        configRepository.save(activeTicketConfig);
    }


    // Retrieve tickets from the active ticket pool
    public void retrieveTickets(int count) {
        if (activeTicketConfig == null || "inactive".equals(activeTicketConfig.getStatus())) {
            throw new TicketPurchaseException("Ticket pool is inactive. No tickets available for purchase.");
        }

        // Check if the total number of tickets exceeds the maximum capacity
        if (activeTicketConfig.getTotalTickets() < count) {
            throw new TicketPurchaseException("Not enough tickets available to complete the purchase.");
        }

        // Update the total number of tickets in the active ticket pool
        int remaining = activeTicketConfig.getTotalTickets() - count;
        activeTicketConfig.setTotalTickets(Math.max(0, remaining));

        // Mark the ticket pool as inactive if all tickets are sold
        if (remaining <= 0) {
            activeTicketConfig.setStatus("inactive"); // Mark pool as inactive if all tickets are sold
        }

        // Save the updated ticket pool configuration
        configRepository.save(activeTicketConfig);
    }

}
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
    private TicketConfigRepository configRepository;

    private TicketConfig activeTicketConfig;

    public void initializeOrUpdatePool(TicketConfig newConfig) {
        Optional<TicketConfig> existingConfig = configRepository.findByStatus("active");

        if (existingConfig.isPresent()) {
            TicketConfig config = existingConfig.get();
            if (config.getTotalTickets() > 0) {
                config.setTotalTickets(newConfig.getTotalTickets());
                config.setMaxTicketCapacity(newConfig.getMaxTicketCapacity());
                config.setTicketReleaseRate(newConfig.getTicketReleaseRate());
                config.setCustomerRetrievalRate(newConfig.getCustomerRetrievalRate());
                configRepository.save(config);
                activeTicketConfig = config;
            } else {
                config.setStatus("inactive");
                configRepository.save(config);

                newConfig.setStatus("active");
                configRepository.save(newConfig);
                activeTicketConfig = newConfig;
            }
        } else {
            newConfig.setStatus("active");
            configRepository.save(newConfig);
            activeTicketConfig = newConfig;
        }
    }

    @Transactional
    public void resetActiveTicketPool() {
        Optional<TicketConfig> existingConfig = configRepository.findByStatus("active");

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

    public int getCurrentTickets() {
        return activeTicketConfig != null ? activeTicketConfig.getTotalTickets() : 0;
    }

    public void addTickets(int count) {
        if (activeTicketConfig == null || "inactive".equals(activeTicketConfig.getStatus())) {
            throw new TicketPurchaseException("Cannot add tickets to an inactive or non-existent ticket pool.");
        }

        activeTicketConfig.setTotalTickets(activeTicketConfig.getTotalTickets() + count);
        configRepository.save(activeTicketConfig);
    }


    public void retrieveTickets(int count) {
        if (activeTicketConfig == null || "inactive".equals(activeTicketConfig.getStatus())) {
            throw new TicketPurchaseException("Ticket pool is inactive. No tickets available for purchase.");
        }

        if (activeTicketConfig.getTotalTickets() < count) {
            throw new TicketPurchaseException("Not enough tickets available to complete the purchase.");
        }

        int remaining = activeTicketConfig.getTotalTickets() - count;
        activeTicketConfig.setTotalTickets(Math.max(0, remaining));

        if (remaining <= 0) {
            activeTicketConfig.setStatus("inactive"); // Mark pool as inactive if all tickets are sold
        }

        configRepository.save(activeTicketConfig);
    }

}
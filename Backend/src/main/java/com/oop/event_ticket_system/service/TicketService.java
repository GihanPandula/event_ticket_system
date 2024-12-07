package com.oop.event_ticket_system.service;

import com.oop.event_ticket_system.domain.TicketConfig;
import com.oop.event_ticket_system.repository.TicketConfigRepository;
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
                // Update existing active ticket pool
                config.setTotalTickets(newConfig.getTotalTickets());
                config.setMaxTicketCapacity(newConfig.getMaxTicketCapacity());
                config.setTicketReleaseRate(newConfig.getTicketReleaseRate());
                config.setCustomerRetrievalRate(newConfig.getCustomerRetrievalRate());
                configRepository.save(config);
                activeTicketConfig = config;
            } else {
                // Mark old pool as inactive and create a new one
                config.setStatus("inactive");
                configRepository.save(config);

                newConfig.setStatus("active");
                configRepository.save(newConfig);
                activeTicketConfig = newConfig;
            }
        } else {
            // No active pool exists, create a new one
            newConfig.setStatus("active");
            configRepository.save(newConfig);
            activeTicketConfig = newConfig;
        }
    }

    public void resetActiveTicketPool(int defaultTickets, int defaultReleaseRate, int defaultRetrievalRate, int defaultCapacity) {
        Optional<TicketConfig> existingConfig = configRepository.findByStatus("active");

        if (existingConfig.isPresent()) {
            TicketConfig config = existingConfig.get();
            config.setTotalTickets(defaultTickets);
            config.setTicketReleaseRate(defaultReleaseRate);
            config.setCustomerRetrievalRate(defaultRetrievalRate);
            config.setMaxTicketCapacity(defaultCapacity);

            configRepository.save(config);
            activeTicketConfig = config;
        } else {
            throw new IllegalStateException("No active ticket pool found to reset.");
        }
    }

    public int getCurrentTickets() {
        return activeTicketConfig != null ? activeTicketConfig.getTotalTickets() : 0;
    }

    public void addTickets(int count) {
        if (activeTicketConfig != null) {
            activeTicketConfig.setTotalTickets(activeTicketConfig.getTotalTickets() + count);
            configRepository.save(activeTicketConfig);
        }
    }

    public void retrieveTickets(int count) {
        if (activeTicketConfig != null) {
            int remaining = activeTicketConfig.getTotalTickets() - count;
            activeTicketConfig.setTotalTickets(Math.max(0, remaining));

            if (remaining <= 0) {
                activeTicketConfig.setStatus("inactive");
            }

            configRepository.save(activeTicketConfig);
        }
    }
}

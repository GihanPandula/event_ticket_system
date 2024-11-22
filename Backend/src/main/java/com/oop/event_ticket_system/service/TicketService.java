package com.oop.event_ticket_system.service;

import com.oop.event_ticket_system.domain.TicketConfig;
import com.oop.event_ticket_system.domain.TicketPool;
import com.oop.event_ticket_system.repository.TicketConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {
    private TicketPool ticketPool;
    @Autowired
    private TicketConfigRepository configRepository;

    public void initializePool(TicketConfig config) {
        if (ticketPool == null) {
            ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());
            configRepository.save(config);
        }
    }

    public void addTickets(int count) throws InterruptedException {
        if (ticketPool != null) {
            ticketPool.addTickets(count);
        }
    }

    public void retrieveTickets(int count) throws InterruptedException {
        if (ticketPool != null) {
            ticketPool.retrieveTickets(count);
        }
    }

    public int getCurrentTickets() {
        return ticketPool != null ? ticketPool.getCurrentTickets() : 0;
    }

    public Optional<TicketConfig> getConfig() {
        return configRepository.findAll().stream().findFirst();
    }
}

package com.oop.event_ticket_system.controller;

import com.oop.event_ticket_system.domain.TicketConfig;
import com.oop.event_ticket_system.dto.TicketConfigRequest;
import com.oop.event_ticket_system.dto.TicketResponse;
import com.oop.event_ticket_system.exception.TicketPurchaseException;
import com.oop.event_ticket_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    public String checkServer() {
        return "API is working!";
    }


    @PostMapping("/initialize")
    public TicketResponse initializeOrUpdatePool(@RequestBody TicketConfigRequest configRequest) {
        TicketConfig config = new TicketConfig();
        config.setTotalTickets(configRequest.getTotalTickets());
        config.setTicketReleaseRate(configRequest.getTicketReleaseRate());
        config.setCustomerRetrievalRate(configRequest.getCustomerRetrievalRate());
        config.setMaxTicketCapacity(configRequest.getMaxTicketCapacity());

        ticketService.initializeOrUpdatePool(config);
        return new TicketResponse(ticketService.getCurrentTickets(), "Ticket pool initialized or updated successfully.");
    }

    @PostMapping("/reset")
    public String resetTicketPool() {
        ticketService.resetActiveTicketPool();
        return "Ticket pool deleted successfully.";
    }

    @PostMapping("/add")
    public TicketResponse addTickets(@RequestParam int count) {
        if (count <= 0) {
            throw new TicketPurchaseException("Ticket count must be greater than zero.");
        }
        ticketService.addTickets(count);
        return new TicketResponse(ticketService.getCurrentTickets(), "Tickets added successfully.");
    }

    @PostMapping("/purchase")
    public TicketResponse purchaseTickets(@RequestParam int count) {
        ticketService.retrieveTickets(count);
        return new TicketResponse(ticketService.getCurrentTickets(), "Tickets purchased successfully.");
    }

    @GetMapping("/current")
    public TicketResponse getCurrentTickets() {
        int currentTickets = ticketService.getCurrentTickets();
        return new TicketResponse(currentTickets, "Retrieved current ticket count.");
    }
}
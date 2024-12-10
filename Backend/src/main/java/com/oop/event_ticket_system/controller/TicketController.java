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
    private TicketService ticketService; // Service which will do all data retrieval/manipulation work

    @GetMapping("/")
    public String checkServer() {
        return "API is working!"; // This will be displayed on the browser when the server is running
    }


    @PostMapping("/initialize")
    public TicketResponse initializeOrUpdatePool(@RequestBody TicketConfigRequest configRequest) {
        TicketConfig config = new TicketConfig(); // Create a new TicketConfig object
        config.setTotalTickets(configRequest.getTotalTickets()); // Set the total tickets
        config.setTicketReleaseRate(configRequest.getTicketReleaseRate());  // Set the ticket release rate
        config.setCustomerRetrievalRate(configRequest.getCustomerRetrievalRate()); // Set the customer retrieval rate
        config.setMaxTicketCapacity(configRequest.getMaxTicketCapacity()); // Set the max ticket capacity

        // Initialize or update the ticket pool
        ticketService.initializeOrUpdatePool(config);
        return new TicketResponse(ticketService.getCurrentTickets(), "Ticket pool initialized or updated successfully.");
    }

    @PostMapping("/reset")
    public String resetTicketPool() {
        ticketService.resetActiveTicketPool(); // Reset the ticket pool
        return "Ticket pool deleted successfully.";
    }

    @PostMapping("/add")
    public TicketResponse addTickets(@RequestParam int count) {
        if (count <= 0) {
            throw new TicketPurchaseException("Ticket count must be greater than zero."); // Throw an exception if the count is less than or equal to zero
        }
        ticketService.addTickets(count); // Add tickets
        return new TicketResponse(ticketService.getCurrentTickets(), "Tickets added successfully.");
    }

    @PostMapping("/purchase")
    public TicketResponse purchaseTickets(@RequestParam int count) {
        ticketService.retrieveTickets(count); // Retrieve tickets
        return new TicketResponse(ticketService.getCurrentTickets(), "Tickets purchased successfully.");   // Return the current ticket count
    }

    @GetMapping("/current")
    public TicketResponse getCurrentTickets() {
        int currentTickets = ticketService.getCurrentTickets(); // Get the current ticket count
        return new TicketResponse(currentTickets, "Retrieved current ticket count."); // Return the current ticket count
    }
}
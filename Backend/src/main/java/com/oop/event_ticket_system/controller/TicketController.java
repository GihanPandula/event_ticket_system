package com.oop.event_ticket_system.controller;

import com.oop.event_ticket_system.domain.TicketConfig;
import com.oop.event_ticket_system.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/initialize")
    public String initializePool(@RequestBody TicketConfig config) {
        ticketService.initializePool(config);
        return "Ticket pool initialized.";
    }

    @PostMapping("/add")
    public String addTickets(@RequestParam int count) throws InterruptedException {
        ticketService.addTickets(count);
        return "Tickets added.";
    }

    @PostMapping("/purchase")
    public String purchaseTickets(@RequestParam int count) throws InterruptedException {
        ticketService.retrieveTickets(count);
        return "Tickets purchased.";
    }

    @GetMapping("/current")
    public int getCurrentTickets() {
        return ticketService.getCurrentTickets();
    }
}

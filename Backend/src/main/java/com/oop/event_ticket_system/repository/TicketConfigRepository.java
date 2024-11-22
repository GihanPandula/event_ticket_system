package com.oop.event_ticket_system.repository;

import com.oop.event_ticket_system.domain.TicketConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketConfigRepository extends JpaRepository<TicketConfig, Long> {
}

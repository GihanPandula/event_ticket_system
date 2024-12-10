package com.oop.event_ticket_system.repository;

import com.oop.event_ticket_system.domain.TicketConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// This will be AUTO IMPLEMENTED by Spring into a Bean called ticketConfigRepository
public interface TicketConfigRepository extends JpaRepository<TicketConfig, Long> {
    Optional<TicketConfig> findByStatus(String status);
}
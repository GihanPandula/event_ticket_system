package com.oop.event_ticket_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.oop.event_ticket_system.repository")
public class EventTicketSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventTicketSystemApplication.class, args);
	}
}
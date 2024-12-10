package com.oop.event_ticket_system.service;

import com.oop.event_ticket_system.domain.Customer;
import com.oop.event_ticket_system.resources.RegisterCustomerResource;
import com.oop.event_ticket_system.resources.CustomerLoginResours;

import java.util.Optional;

public interface CustomerService {

    // Get all customers from the database
    Optional<Customer> getCustomerBy(String userName);

    // Get all customers from the database
    CustomerLoginResours login(CustomerLoginResours customerLoginResours);

    // Register a new customer to the database and return the customer id
    String registerCustomer(RegisterCustomerResource registerCustomerResource);
}
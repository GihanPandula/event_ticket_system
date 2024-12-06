package com.oop.event_ticket_system.service;

import com.oop.event_ticket_system.domain.Customer;
import com.oop.event_ticket_system.resources.RegisterCustomerResource;
import com.oop.event_ticket_system.resources.CustomerLoginResours;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getCustomerBy(String userName);

    CustomerLoginResours login(CustomerLoginResours customerLoginResours);

    String registerCustomer(RegisterCustomerResource registerCustomerResource);
}
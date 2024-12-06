package com.oop.event_ticket_system.service.impl;

import com.oop.event_ticket_system.domain.Customer;
import com.oop.event_ticket_system.repository.CustomerRepository;
import com.oop.event_ticket_system.resources.RegisterCustomerResource;
import com.oop.event_ticket_system.resources.CustomerLoginResours;
import com.oop.event_ticket_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> getCustomerBy(String customerName) {
        return customerRepository.getCustomerBy(customerName);
    }

    @Override
    public CustomerLoginResours login(CustomerLoginResours customerLoginResours) {
        Optional<Customer> customer = customerRepository.findByEmail(customerLoginResours.getEmail());
        if (customer.isPresent()) {
            if (customer.get().getPassword().equals(customerLoginResours.getPassword())) {
                return mapCustomerToCustomerLoginResours(customer.get());
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public String registerCustomer(RegisterCustomerResource registerCustomerResource) {
        if (!registerCustomerResource.getPassword().equals(registerCustomerResource.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        if (registerCustomerResource.getContactNumber().length() != 10) {
            throw new RuntimeException("Contact number must be exactly 10 digits");
        }

        if (!registerCustomerResource.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Invalid email format");
        }

        Optional<Customer> existingCustomer = customerRepository.findByEmailOrFirstName(registerCustomerResource.getEmail(), registerCustomerResource.getFirstName());

        if (existingCustomer.isPresent()) {
            throw new RuntimeException("Customer already exists with the given email or customername");
        }

        Customer customer = mapRegisterCustomerResourceToCustomer(registerCustomerResource);
        customerRepository.save(customer);
        return "Customer registered successfully";
    }

    private CustomerLoginResours mapCustomerToCustomerLoginResours(Customer customer) {
        CustomerLoginResours customerLoginResours = new CustomerLoginResours();
        customerLoginResours.setEmail(customer.getEmail());
        customerLoginResours.setPassword(customer.getPassword());
        return customerLoginResours;
    }

    private Customer mapRegisterCustomerResourceToCustomer(RegisterCustomerResource registerCustomerResource) {
        Customer customer = new Customer();
        customer.setEmail(registerCustomerResource.getEmail());
        customer.setPassword(registerCustomerResource.getPassword());
        customer.setConfirmationPassword(registerCustomerResource.getConfirmPassword());
        customer.setFirstName(registerCustomerResource.getFirstName());
        customer.getLastName(registerCustomerResource.getLastName());
        customer.setLastName(registerCustomerResource.getLastName());
        customer.setContactNumber(registerCustomerResource.getContactNumber());
        return customer;
    }
}
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
// This class implements the CustomerService interface and provides the implementation for the methods declared in the interface.
class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    // This constructor is used to inject the CustomerRepository bean into the CustomerServiceImpl class.
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    // This method is used to get a customer by the customer name.
    public Optional<Customer> getCustomerBy(String customerName) {
        return customerRepository.getCustomerBy(customerName);
    }

    @Override
    // This method is used to get a customer by the customer email.
    public CustomerLoginResours login(CustomerLoginResours customerLoginResours) {
        Optional<Customer> customer = customerRepository.findByEmail(customerLoginResours.getEmail());
        if (customer.isPresent()) {
            if (customer.get().getPassword().equals(customerLoginResours.getPassword())) {
                return mapCustomerToCustomerLoginResours(customer.get());  // This method is used to map the customer to the CustomerLoginResours object.
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    // This method is used to register a customer.
    public String registerCustomer(RegisterCustomerResource registerCustomerResource) {
        // This block of code is used to validate the customer details.
        if (!registerCustomerResource.getPassword().equals(registerCustomerResource.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        // This block of code is used to validate the customer details.
        if (registerCustomerResource.getContactNumber().length() != 10) {
            throw new RuntimeException("Contact number must be exactly 10 digits");
        }

        // This block of code is used to validate the customer details.
        if (!registerCustomerResource.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Invalid email format");
        }

        // This block of code is used to validate the customer details.
        Optional<Customer> existingCustomer = customerRepository.findByEmailOrFirstName(registerCustomerResource.getEmail(), registerCustomerResource.getFirstName());

        // This block of code is used to validate the customer details.
        if (existingCustomer.isPresent()) {
            throw new RuntimeException("Customer already exists with the given email or customername");
        }

        // This block of code is used to map the RegisterCustomerResource object to the Customer object.
        Customer customer = mapRegisterCustomerResourceToCustomer(registerCustomerResource);
        customerRepository.save(customer);
        return "Customer registered successfully";
    }

    // This method is used to map the customer to the CustomerLoginResours object.
    private CustomerLoginResours mapCustomerToCustomerLoginResours(Customer customer) {
        CustomerLoginResours customerLoginResours = new CustomerLoginResours();
        customerLoginResours.setEmail(customer.getEmail());
        customerLoginResours.setPassword(customer.getPassword());
        return customerLoginResours;
    }

    // This method is used to map the RegisterCustomerResource object to the Customer object.
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
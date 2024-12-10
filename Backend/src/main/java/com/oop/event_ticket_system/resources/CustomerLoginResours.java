package com.oop.event_ticket_system.resources;

import lombok.Data;

@Data
// This class is used to get the login details of the customer
public class CustomerLoginResours {
    private String email;
    private String password;
}
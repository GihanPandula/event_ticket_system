package com.oop.event_ticket_system.resources;

import lombok.Data;

@Data
public class VendorLoginResource { // Resource class for Vendor login
    private String email; // Email of the vendor
    private String password; // Password of the vendor
}

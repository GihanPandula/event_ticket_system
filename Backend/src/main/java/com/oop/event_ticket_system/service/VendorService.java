package com.oop.event_ticket_system.service;

import com.oop.event_ticket_system.domain.Vendor;
import com.oop.event_ticket_system.resources.RegisterVendorResource;
import com.oop.event_ticket_system.resources.VendorLoginResource;

import java.util.Optional;

// VendorService interface provides the methods related to the vendor
public interface VendorService {

    // Method to get the vendor by the username of the vendor
    Optional<Vendor> getVendorBy(String userName);

    // Method to get the vendor by the id of the vendor
    VendorLoginResource login(VendorLoginResource vendorLoginResource);

    // Method to register the vendor by the vendor details
    String registerVendor(RegisterVendorResource registerVendorResource);
}

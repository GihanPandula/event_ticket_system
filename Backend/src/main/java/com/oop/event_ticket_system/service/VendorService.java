package com.oop.event_ticket_system.service;

import com.oop.event_ticket_system.domain.Vendor;
import com.oop.event_ticket_system.resources.RegisterVendorResource;
import com.oop.event_ticket_system.resources.VendorLoginResource;

import java.util.Optional;

public interface VendorService {

    Optional<Vendor> getVendorBy(String userName);

    VendorLoginResource login(VendorLoginResource vendorLoginResource);

    String registerVendor(RegisterVendorResource registerVendorResource);
}

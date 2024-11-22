package com.oop.event_ticket_system.service.impl;

import com.oop.event_ticket_system.domain.Vendor;
import com.oop.event_ticket_system.repository.VendorRepository;
import com.oop.event_ticket_system.resources.RegisterVendorResource;
import com.oop.event_ticket_system.resources.VendorLoginResource;
import com.oop.event_ticket_system.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Optional<Vendor> getVendorBy(String vendorName) {
        return vendorRepository.getVendorBy(vendorName);
    }

    @Override
    public VendorLoginResource login(VendorLoginResource vendorLoginResource) {
        Optional<Vendor> vendor = vendorRepository.findByEmail(vendorLoginResource.getEmail());
        if (vendor.isPresent()) {
            if (vendor.get().getPassword().equals(vendorLoginResource.getPassword())) {
                return mapVendorToVendorLoginResource(vendor.get());
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("Vendor not found");
        }
    }

    @Override
    public String registerVendor(RegisterVendorResource registerVendorResource) {
        if (!registerVendorResource.getPassword().equals(registerVendorResource.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        if (registerVendorResource.getContactNumber().length() != 10) {
            throw new RuntimeException("Contact number must be exactly 10 digits");
        }

        if (!registerVendorResource.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Invalid email format");
        }

        Optional<Vendor> existingVendor = vendorRepository.findByEmailOrFirstName(registerVendorResource.getEmail(), registerVendorResource.getFirstName());

        if (existingVendor.isPresent()) {
            throw new RuntimeException("Vendor already exists with the given email or name");
        }

        Vendor vendor = mapRegisterVendorResourceToVendor(registerVendorResource);
        vendorRepository.save(vendor);
        return "Vendor registered successfully";
    }

    private VendorLoginResource mapVendorToVendorLoginResource(Vendor vendor) {
        VendorLoginResource vendorLoginResource = new VendorLoginResource();
        vendorLoginResource.setEmail(vendor.getEmail());
        vendorLoginResource.setPassword(vendor.getPassword());
        return vendorLoginResource;
    }

    private Vendor mapRegisterVendorResourceToVendor(RegisterVendorResource registerVendorResource) {
        Vendor vendor = new Vendor();
        vendor.setEmail(registerVendorResource.getEmail());
        vendor.setPassword(registerVendorResource.getPassword());
        vendor.setConfirmationPassword(registerVendorResource.getConfirmPassword());
        vendor.setFirstName(registerVendorResource.getFirstName());
        vendor.setLastName(registerVendorResource.getLastName());
        vendor.setContactNumber(registerVendorResource.getContactNumber());
        return vendor;
    }
}
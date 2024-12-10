package com.oop.event_ticket_system.controller;

import com.oop.event_ticket_system.resources.RegisterVendorResource;
import com.oop.event_ticket_system.resources.VendorLoginResource;
import com.oop.event_ticket_system.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:65230")
@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService; // VendorService dependency

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    } // Constructor injection

    @PostMapping("/register")
    public ResponseEntity<String> registerVendor(@RequestBody RegisterVendorResource registerVendorResource) {
        try {
            String result = vendorService.registerVendor(registerVendorResource); // Register vendor
            return ResponseEntity.ok(result); // Return success message
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Return error message
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody VendorLoginResource vendorLoginResource) {
        try {
            return ResponseEntity.ok(vendorService.login(vendorLoginResource)); // Return vendor details
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Return error message
        }
    }
}




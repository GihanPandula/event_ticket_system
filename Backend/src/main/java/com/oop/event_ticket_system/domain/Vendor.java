package com.oop.event_ticket_system.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "vendors")
@Data
public class Vendor implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L; // Unique identifier for Serializable class

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
        @Column(name = "vendor_id", nullable = false) // Map to vendor_id column, not null
        private Long vendorId;

        @Column(name = "first_name", length = 50, nullable = false) // Map to first_name column, max length 50, not null
        private String firstName;

        @Column(name = "last_name", length = 50, nullable = false) // Map to last_name column, max length 50, not null
        private String lastName;

        @Column(name = "email", length = 100, nullable = false) // Map to email column, max length 100, not null
        private String email;

        @Column(name = "contact_number", length = 10, nullable = false) // Map to contact_number column, max length 10, not null
        private String contactNumber;

        @Column(name = "password", length = 255, nullable = false) // Map to password column, max length 255, not null
        private String password;

        @Column(name = "confirmation_password", length = 255, nullable = false) // Map to confirmation_password column, max length 255, not null
        private String confirmationPassword;
}
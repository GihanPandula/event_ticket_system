package com.oop.event_ticket_system.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "customers")
@Data
public class Customer implements Serializable { // Serializable is used to ensure that the object can be serialized and deserialized

    @Serial
    // The value of the serialVersionUID is used to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY is used to specify that the value of the annotated data member will be automatically generated
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "contact_number", length = 10, nullable = false)
    private String contactNumber;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "confirmation_password", length = 255, nullable = false)
    private String confirmationPassword;

    // Getters and Setters
    public void getLastName(String lastName) {
        this.lastName = lastName;
    }
}
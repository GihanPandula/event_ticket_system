package com.oop.event_ticket_system.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

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

    public void getLastName(String lastName) {
        this.lastName = lastName;
    }
}
package com.oop.event_ticket_system.repository;

import com.oop.event_ticket_system.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT u FROM Customer u WHERE u.email = :email")
    Optional<Customer> getCustomerBy(@Param("email") String email);

    Optional<Customer> findByEmail(String email);

    @Query("SELECT u FROM Customer u WHERE u.email = :email OR u.firstName = :firstName")
    Optional<Customer> findByEmailOrFirstName(@Param("email") String email, @Param("firstName") String firstName);

}
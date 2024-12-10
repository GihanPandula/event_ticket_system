package com.oop.event_ticket_system.repository;

import com.oop.event_ticket_system.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
// Vendor Repository which extends JpaRepository to use built-in CRUD functions and
// custom queries are written using @Query annotation to fetch data from the database using JPQL queries.
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    // Custom query to get vendor by email using JPQL query
    @Query("SELECT u FROM Vendor u WHERE u.email = :email")
    Optional<Vendor> getVendorBy(@Param("email") String email); // email is the parameter to be passed to the query

    Optional<Vendor> findByEmail(String email); // built-in query to get vendor by email

    // Custom query to get vendor by email or first name using JPQL query
    @Query("SELECT u FROM Vendor u WHERE u.email = :email OR u.firstName = :firstName")
    Optional<Vendor> findByEmailOrFirstName(@Param("email") String email, @Param("firstName") String firstName); // custom query to get vendor by email or first name
}

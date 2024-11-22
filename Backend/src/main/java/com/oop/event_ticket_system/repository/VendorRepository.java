package com.oop.event_ticket_system.repository;

import com.oop.event_ticket_system.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Query("SELECT u FROM Vendor u WHERE u.email = :email")
    Optional<Vendor> getVendorBy(@Param("email") String email);

    Optional<Vendor> findByEmail(String email);

    @Query("SELECT u FROM Vendor u WHERE u.email = :email OR u.firstName = :firstName")
    Optional<Vendor> findByEmailOrFirstName(@Param("email") String email, @Param("firstName") String firstName);
}

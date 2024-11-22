package com.oop.event_ticket_system.repository;

import com.oop.event_ticket_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> getUserBy(@Param("email") String email);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email OR u.firstName = :firstName")
    Optional<User> findByEmailOrFirstName(@Param("email") String email, @Param("firstName") String firstName);
}
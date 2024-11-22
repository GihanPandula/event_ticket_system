package com.oop.event_ticket_system.service;

import com.oop.event_ticket_system.domain.User;
import com.oop.event_ticket_system.resources.RegisterUserResource;
import com.oop.event_ticket_system.resources.UserLoginResours;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserBy(String userName);

    UserLoginResours login(UserLoginResours userLoginResours);

    String registerUser(RegisterUserResource RegisterUserResource);
}
package com.oop.event_ticket_system.service.impl;

import com.oop.event_ticket_system.domain.User;
import com.oop.event_ticket_system.repository.UserRepository;
import com.oop.event_ticket_system.resources.RegisterUserResource;
import com.oop.event_ticket_system.resources.UserLoginResours;
import com.oop.event_ticket_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserBy(String userName) {
        return userRepository.getUserBy(userName);
    }

    @Override
    public UserLoginResours login(UserLoginResours userLoginResours) {
        Optional<User> user = userRepository.findByEmail(userLoginResours.getEmail());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(userLoginResours.getPassword())) {
                return mapUserToUserLoginResours(user.get());
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public String registerUser(RegisterUserResource registerUserResource) {
        if (!registerUserResource.getPassword().equals(registerUserResource.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        if (registerUserResource.getContactNumber().length() != 10) {
            throw new RuntimeException("Contact number must be exactly 10 digits");
        }

        if (!registerUserResource.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new RuntimeException("Invalid email format");
        }

        Optional<User> existingUser = userRepository.findByEmailOrFirstName(registerUserResource.getEmail(), registerUserResource.getFirstName());

        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with the given email or username");
        }

        User user = mapRegisterUserResourceToUser(registerUserResource);
        userRepository.save(user);
        return "User registered successfully";
    }

    private UserLoginResours mapUserToUserLoginResours(User user) {
        UserLoginResours userLoginResours = new UserLoginResours();
        userLoginResours.setEmail(user.getEmail());
        userLoginResours.setPassword(user.getPassword());
        return userLoginResours;
    }

    private User mapRegisterUserResourceToUser(RegisterUserResource registerUserResource) {
        User user = new User();
        user.setEmail(registerUserResource.getEmail());
        user.setPassword(registerUserResource.getPassword());
        user.setConfirmationPassword(registerUserResource.getConfirmPassword());
        user.setFirstName(registerUserResource.getFirstName());
        user.getLastName(registerUserResource.getLastName());
        user.setLastName(registerUserResource.getLastName());
        user.setContactNumber(registerUserResource.getContactNumber());
        return user;
    }
}
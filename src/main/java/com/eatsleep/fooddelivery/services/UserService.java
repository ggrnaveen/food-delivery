package com.eatsleep.fooddelivery.services;

import com.eatsleep.fooddelivery.exceptions.ResourceNotFoundException;
import com.eatsleep.fooddelivery.models.User;
import com.eatsleep.fooddelivery.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user

    @Transactional
    public User createUser(User user) {

        user.setId(UUID.randomUUID().toString());

        return userRepository.save(user);
    }

    // Retrieve a user by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findByIdAndNotDeleted(id);
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update user details
    @Transactional
    public User updateUser(String id, User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    // Delete a user
    @Transactional
    public void deleteUser(String id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

    public String generateUUID() {
        // Generates a random UUID (version 4)
        UUID uuid = UUID.randomUUID();
        return uuid.toString(); // Convert UUID to a string representation
    }
}

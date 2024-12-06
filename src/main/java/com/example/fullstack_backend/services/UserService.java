package com.example.fullstack_backend.services;

import com.example.fullstack_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a user
    public boolean registerUser(String email, String password, String role) {
        if (userRepository.existsByEmail(email)) {
            return false;  // Return false if the user already exists
        }
        userRepository.addUser(email, password, role);
        return true; // Return true if the user is successfully added
    }

    // Login logic (This is just an example, ensure you have proper login verification)
    public boolean login(String email, String password) {
        // Implement actual login logic, such as comparing hashed passwords
        return userRepository.findUserByEmail(email) != null;
    }

    // Update user (using the update query in UserRepository)
    public boolean updateUser(int userId, String email, String password, String role) {
        int updatedCount = userRepository.updateUser(userId, email, password, role);
        return updatedCount > 0;  // Return true if the update was successful
    }

    // Delete user
    public boolean deleteUser(String email) {
        try {
            userRepository.deleteUser(email);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

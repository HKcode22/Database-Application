package com.example.fullstack_backend.services;

import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.repository.UserRepository;
import com.example.fullstack_backend.repository.CustomerRepository;
import com.example.fullstack_backend.repository.RestaurantRepository;
import dto.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public boolean registerUser(String email, String password, String role, String name, String phoneNumber,
                                String streetAddress, String city, String state, String zipCode, Integer categoryId,
                                String openingHours) {
        if (userRepository.existsByEmail(email)) {
            return false;  // Return false if the user already exists
        }

        // Save the user
        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // Ideally, hash the password before saving
        user.setRole(mapRole(role)); // Map role to enum value
        User savedUser = userRepository.save(user);

        // Add to the appropriate table based on the role
        if (role.equalsIgnoreCase("customer")) {
            // Add customer details
            customerRepository.addCustomer(savedUser.getUserId(), name, phoneNumber);
        } else if (role.equalsIgnoreCase("restaurant_admin")) {
            // Add restaurant details using DTO
            RestaurantDTO restaurantDTO = new RestaurantDTO();
            restaurantDTO.setUserId(savedUser.getUserId());
            restaurantDTO.setName(name);
            restaurantDTO.setEmail(email);
            restaurantDTO.setStreetAddress(streetAddress);
            restaurantDTO.setCity(city);
            restaurantDTO.setState(state);
            restaurantDTO.setZipCode(zipCode);
            restaurantDTO.setCategoryId(categoryId);
            restaurantDTO.setPhoneNumber(phoneNumber);
            restaurantDTO.setOpeningHours(openingHours);

            // Use repository to add the restaurant
            restaurantRepository.addRestaurant(restaurantDTO);
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        return true;
    }

    // Helper function to map the role string to User.Role enum
    private User.Role mapRole(String role) {
        switch (role.toLowerCase()) {
            case "customer":
                return User.Role.customer;
            case "restaurant_admin":
                return User.Role.restaurant_admin;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    // Login logic (This is just an example, ensure you have proper login verification)
    public boolean login(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
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

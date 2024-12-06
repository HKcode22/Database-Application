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
        user.setRole(User.Role.valueOf(role.toLowerCase())); // Use lowercase to match enum
        User savedUser = userRepository.save(user);

        // Add to the appropriate table based on the role
        if (role.equalsIgnoreCase("customer")) {
            // Add customer details
            customerRepository.addCustomer(savedUser.getUserId(), name, phoneNumber);
        } else if (role.equalsIgnoreCase("restaurant_admin")) {
            // Add restaurant details
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
            restaurantRepository.addRestaurant(
                    restaurantDTO.getUserId(),
                    restaurantDTO.getName(),
                    restaurantDTO.getEmail(),
                    restaurantDTO.getStreetAddress(),
                    restaurantDTO.getCity(),
                    restaurantDTO.getState(),
                    restaurantDTO.getZipCode(),
                    restaurantDTO.getCategoryId(),
                    restaurantDTO.getPhoneNumber(),
                    restaurantDTO.getOpeningHours()
            );
        } else {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        return true;
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

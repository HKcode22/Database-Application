package com.example.fullstack_backend.controller;

import dto.UserDTO;
import com.example.fullstack_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a customer or restaurant admin
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Map<String, Object> userData) {
        try {
            // Extract common fields
            String email = (String) userData.get("email");
            String password = (String) userData.get("password");
            String role = (String) userData.get("role");

            // Extract customer or restaurant details based on role
            if (role.equalsIgnoreCase("customer")) {
                String name = (String) userData.get("name");
                String phoneNumber = (String) userData.get("phoneNumber");

                boolean registered = userService.registerUser(email, password, role, name, phoneNumber, null, null, null, null, null, null);
                if (registered) {
                    return new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED);
                }
            } else if (role.equalsIgnoreCase("restaurant_admin")) {
                // Make sure `restaurantDetails` exists before trying to cast it
                Object restaurantDetailsObj = userData.get("restaurantDetails");
                if (restaurantDetailsObj instanceof Map) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> restaurantDetails = (Map<String, Object>) restaurantDetailsObj;

                    // Extract restaurant details from nested object
                    String name = (String) restaurantDetails.get("name");
                    String streetAddress = (String) restaurantDetails.get("streetAddress");
                    String city = (String) restaurantDetails.get("city");
                    String state = (String) restaurantDetails.get("state");
                    String zipCode = (String) restaurantDetails.get("zipCode");
                    String phoneNumber = (String) restaurantDetails.get("phoneNumber");
                    String openingHours = (String) restaurantDetails.get("openingHours");

                    boolean registered = userService.registerUser(email, password, role, name, phoneNumber, streetAddress, city, state, zipCode, null, openingHours);
                    if (registered) {
                        return new ResponseEntity<>("Restaurant registered successfully", HttpStatus.CREATED);
                    }
                } else {
                    return new ResponseEntity<>("Invalid restaurant details provided", HttpStatus.BAD_REQUEST);
                }
            }

            return new ResponseEntity<>("Error registering user: Email already taken", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error registering user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        boolean success = userService.login(userDTO.getEmail(), userDTO.getPassword());
        return success ?
                new ResponseEntity<>("Login successful", HttpStatus.OK) :
                new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    // Log out
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }

    // Update User
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO, @RequestParam int userId) {
        boolean success = userService.updateUser(userId, userDTO.getEmail(), userDTO.getPassword(), userDTO.getRole());
        return success ?
                new ResponseEntity<>("User updated successfully", HttpStatus.OK) :
                new ResponseEntity<>("Error updating user", HttpStatus.BAD_REQUEST);
    }

    // Delete User
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserDTO userDTO) {
        boolean success = userService.deleteUser(userDTO.getEmail());
        return success ?
                new ResponseEntity<>("User deleted successfully", HttpStatus.OK) :
                new ResponseEntity<>("Error deleting user", HttpStatus.BAD_REQUEST);
    }
}
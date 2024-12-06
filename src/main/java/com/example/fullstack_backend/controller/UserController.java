package com.example.fullstack_backend.controller;

import dto.UserDTO;
import com.example.fullstack_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a customer or restaurant admin
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody UserDTO userDTO) {
        boolean registered = userService.registerUser(
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRole(),
                userDTO.getName(),
                userDTO.getPhoneNumber(),
                userDTO.getStreetAddress(),
                userDTO.getCity(),
                userDTO.getState(),
                userDTO.getZipCode(),
                userDTO.getCategoryId(),
                userDTO.getOpeningHours()
        );

        if (registered) {
            return new ResponseEntity<>("registered successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error registering customer: Email already taken", HttpStatus.BAD_REQUEST);
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

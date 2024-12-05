package com.example.restaurantreservation.controllers;

import com.example.restaurantreservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Register a customer
    @PostMapping("/register/customer")
    public ResponseEntity<String> registerCustomer(@RequestParam String email, 
                                                   @RequestParam String password, 
                                                   @RequestParam String name, 
                                                   @RequestParam String phoneNumber,
                                                   @RequestParam String role) {
        boolean success = userService.registerUser(email, password, name, phoneNumber, role);
        return success ? 
               new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED) : 
               new ResponseEntity<>("Error registering customer", HttpStatus.BAD_REQUEST);
    }

    //Register a restaurant user
    @PostMapping("/register/restaurant")
    public ResponseEntity<String> registerRestaurant(@RequestParam String email, 
                                                     @RequestParam String password, 
                                                     @RequestParam String restaurantName, 
                                                     @RequestParam String address, 
                                                     @RequestParam String category) {
        boolean success = userService.registerUser(email, password, restaurantName, address, category);
        return success ? 
               new ResponseEntity<>("Restaurant registered successfully", HttpStatus.CREATED) : 
               new ResponseEntity<>("Error registering restaurant", HttpStatus.BAD_REQUEST);
    }

    //Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        boolean success = userService.login(email, password);
        return success ? 
               new ResponseEntity<>("Login successful", HttpStatus.OK) : 
               new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }

    //Log out
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }
}

package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.Customer;
import com.example.fullstack_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // Add a new customer
    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestParam int userId,
                                              @RequestParam String name,
                                              @RequestParam String phoneNumber) {
        try {
            customerRepository.addCustomer(userId, name, phoneNumber);
            return new ResponseEntity<>("Customer added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding customer: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing customer
    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable int customerId,
                                                 @RequestParam String name,
                                                 @RequestParam String phoneNumber) {
        if (!customerRepository.existsById(customerId)) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }

        try {
            int rowsUpdated = customerRepository.updateCustomer(customerId, name, phoneNumber);
            if (rowsUpdated > 0) {
                return new ResponseEntity<>("Customer updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Customer update failed", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating customer: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get customer profile by userId
    @GetMapping("/profile")
    public ResponseEntity<Customer> getCustomerProfile(@RequestParam int userId) {
        // Assuming your CustomerRepository has a method to find a customer by userId
        Optional<Customer> customerOpt = customerRepository.findById(userId);

        if (!customerOpt.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Customer not found
        }

        return new ResponseEntity<>(customerOpt.get(), HttpStatus.OK);  // Return the customer data
    }

    // Delete a customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        if (!customerRepository.existsById(customerId)) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }

        try {
            customerRepository.deleteCustomer(customerId);
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting customer: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

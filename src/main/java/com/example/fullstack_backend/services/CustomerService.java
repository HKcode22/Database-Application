package com.example.fullstack_backend.services;

import com.example.fullstack_backend.model.Customer;
import com.example.fullstack_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Add a new customer
    public void addCustomer(Customer customer) {
        customerRepository.addCustomer(customer.getUser().getUserId(), customer.getName(), customer.getPhoneNumber());
    }

    // Update an existing customer
    public boolean updateCustomer(int customerId, Customer updatedCustomer) {
        return customerRepository.updateCustomer(customerId, updatedCustomer.getName(), updatedCustomer.getPhoneNumber()) > 0;
    }

    // Delete a customer
    public boolean deleteCustomer(int customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteCustomer(customerId);
            return true;
        }
        return false;
    }
}
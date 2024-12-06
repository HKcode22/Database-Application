package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Add a new customer
    @Transactional
    @Query(value = "INSERT INTO Customers (user_id, name, phone_number, created_at) " +
            "VALUES (:userId, :name, :phoneNumber, CURRENT_TIMESTAMP)", nativeQuery = true)
    void addCustomer(@Param("userId") int userId, @Param("name") String name, @Param("phoneNumber") String phoneNumber);

    // Update an existing customer
    @Transactional
    @Query(value = "UPDATE Customers SET name = :name, phone_number = :phoneNumber WHERE customer_id = :customerId",
            nativeQuery = true)
    int updateCustomer(@Param("customerId") int customerId, @Param("name") String name, @Param("phoneNumber") String phoneNumber);

    // Delete a customer
    @Transactional
    @Query(value = "DELETE FROM Customers WHERE customer_id = :customerId", nativeQuery = true)
    void deleteCustomer(@Param("customerId") int customerId);

    // Check if a customer exists by ID (if needed for checking existence before deletion or update)
    @Query(value = "SELECT COUNT(*) > 0 FROM Customers WHERE customer_id = :customerId", nativeQuery = true)
    boolean existsById(@Param("customerId") int customerId);
}

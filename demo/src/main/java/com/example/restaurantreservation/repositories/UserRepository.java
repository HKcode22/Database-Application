package com.example.restaurantreservation.repositories;

import com.example.restaurantreservation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
/* 
    // Find a user by email using native SQL
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User findUserByEmail(String email);

    // Check if a user exists by email using native SQL
    @Query(value = "SELECT EXISTS(SELECT 1 FROM users WHERE email = :email)", nativeQuery = true)
    boolean existsByEmail(String email);

    // Add a new user using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users (email, password, role) VALUES (:email, :password, :role)", nativeQuery = true)
    void addUser(String email, String password, Enum role);

    // Update user using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET password = :password, name = :name, phone_number = :phoneNumber, role = :role WHERE email = :email", nativeQuery = true)
    int updateUser(String email, String password, String name, String phoneNumber, String role);

    // Delete user using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE email = :email", nativeQuery = true)
    void deleteUser(String email);
    */
}

package com.example.fullstack_backend.repository;

import com.example.fullstack_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find a user by email using native SQL
    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    User findUserByEmail(String email);

    // Check if a user exists by email using native SQL
    default boolean existsByEmail(String email) {
        return countByEmail(email) > 0;
    }

    @Query("SELECT COUNT(u) FROM User u WHERE u.email = :email")
    int countByEmail(@Param("email") String email);

    // Add a new user using native SQL (no need to insert user_id, it will auto-increment)
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Users (email, password, role) VALUES (:email, :password, :role)", nativeQuery = true)
    void addUser(String email, String password, String role);

    // Update user using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE Users SET password = :password, role = :role, email = :email WHERE user_id = :user_id", nativeQuery = true)
    int updateUser(int user_id, String email, String password, String role);

    // Delete user using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Users WHERE email = :email", nativeQuery = true)
    void deleteUser(String email);
}

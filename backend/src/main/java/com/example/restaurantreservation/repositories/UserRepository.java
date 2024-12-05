package main.java.com.example.restaurantreservation.repositories;

import main.java.com.example.restaurantreservation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find a user by email using native SQL
    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    User findUserByEmail(String email);

    // Check if a user exists by email using native SQL
    @Query(value = "SELECT EXISTS(SELECT 1 FROM Users WHERE email = :email)", nativeQuery = true)
    boolean existsByEmail(String email);

    // Add a new user using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Users (email, password, name, phone_number, role) VALUES (:email, :password, :name, :phoneNumber, :role)", nativeQuery = true)
    void addUser(String email, String password, String name, String phoneNumber, String role);

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

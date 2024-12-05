package main.java.com.example.restaurantreservation.services;

import main.java.com.example.restaurantreservation.models.User;
import main.java.com.example.restaurantreservation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public boolean registerUser(String email, String password, String name, String phoneNumber, String role) {
        if (userExists(email)) {
            return false; // Prevent duplicate registration
        }
        userRepository.addUser(email, password, name, phoneNumber, role);
        return true;
    }


    @Transactional
    public boolean login(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    @Transactional
    public boolean updateUser(int userId, String email, String password, String role) {
        return userRepository.updateUser(userId, email, password, role) > 0;
    }

    @Transactional
    public boolean deleteUser(String email) {
        if (!userExists(email)) {
            return false;
        }
        userRepository.deleteUser(email);
        return true;
    }
}

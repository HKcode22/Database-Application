package com.example.restaurantreservation.services;

import com.example.restaurantreservation.models.User;
import com.example.restaurantreservation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean registerUser(String email, String password, String name, String phoneNumber, String role) {
        if (!userExists(email)){
            userRepository.addUser(email, password, name, phoneNumber, role);
            return true;
        }
        return false;
    }

    public boolean login(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true; 
        }
        return false;
    }

    public boolean updateUser(String email, String password, String name, String phoneNumber, String role) {
        return userRepository.updateUser(email, password, name, phoneNumber, role) > 0;
    }

    public boolean deleteUser(String email) {
        userRepository.deleteUser(email);
        return true;
    }
}

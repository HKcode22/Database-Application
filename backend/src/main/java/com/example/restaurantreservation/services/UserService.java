package main.java.com.example.restaurantreservation.services;

import main.java.com.example.restaurantreservation.models.User;
import main.java.com.example.restaurantreservation.repositories.UserRepository;
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

    public void registerUser(String email, String password, String name, String phoneNumber, String role) {
        userRepository.addUser(email, password, name, phoneNumber, role);
    }

    public boolean updateUser(String email, String password, String name, String phoneNumber, String role) {
        return userRepository.updateUser(email, password, name, phoneNumber, role) > 0;
    }

    public boolean deleteUser(String email) {
        userRepository.deleteUser(email);
        return true;
    }
}

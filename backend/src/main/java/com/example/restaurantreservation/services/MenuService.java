package main.java.com.example.restaurantreservation.services;

import main.java.com.example.restaurantreservation.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    // Add a new menu item
    public void addMenuItem(int restaurantId, String itemName, double itemPrice, String description) {
        menuRepository.addMenuItem(restaurantId, itemName, itemPrice, description);
    }

    // Update an existing menu item
    public boolean updateMenuItem(int menuId, String itemName, double itemPrice, String description) {
        return menuRepository.updateMenuItem(menuId, itemName, itemPrice, description) > 0;
    }

    // Delete a menu item
    public boolean deleteMenuItem(int menuId) {
        menuRepository.deleteMenuItem(menuId);
        return true;
    }
}

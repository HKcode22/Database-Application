package main.java.com.example.restaurantreservation.controllers;

import main.java.com.example.restaurantreservation.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // Add a new menu item
    @PostMapping("/add")
    public ResponseEntity<String> addMenuItem(@RequestParam int restaurantId,
                                              @RequestParam String itemName,
                                              @RequestParam double itemPrice,
                                              @RequestParam(required = false) String description) {
        try {
            menuService.addMenuItem(restaurantId, itemName, itemPrice, description);
            return new ResponseEntity<>("Menu item added successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add menu item: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing menu item
    @PutMapping("/{menuId}/update")
    public ResponseEntity<String> updateMenuItem(@PathVariable int menuId,
                                                 @RequestParam String itemName,
                                                 @RequestParam double itemPrice,
                                                 @RequestParam(required = false) String description) {
        boolean success = menuService.updateMenuItem(menuId, itemName, itemPrice, description);
        if (success) {
            return new ResponseEntity<>("Menu item updated successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update menu item.", HttpStatus.BAD_REQUEST);
    }

    // Delete a menu item
    @DeleteMapping("/{menuId}/delete")
    public ResponseEntity<String> deleteMenuItem(@PathVariable int menuId) {
        try {
            menuService.deleteMenuItem(menuId);
            return new ResponseEntity<>("Menu item deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete menu item: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

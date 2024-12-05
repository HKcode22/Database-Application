package main.java.com.example.restaurantreservation.controllers;

import main.java.com.example.restaurantreservation.models.RestaurantTable;
import main.java.com.example.restaurantreservation.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    // Get all tables for a specific restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<RestaurantTable>> getTablesByRestaurant(@PathVariable int restaurantId) {
        List<RestaurantTable> tables = tableService.getTablesByRestaurantId(restaurantId);
        if (!tables.isEmpty()) {
            return new ResponseEntity<>(tables, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add a new table for a specific restaurant
    @PostMapping("/add")
    public ResponseEntity<String> addTable(@RequestParam int restaurantId,
                                           @RequestParam int seats) {
        try {
            tableService.addTable(restaurantId, seats);
            return new ResponseEntity<>("Table added successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add table: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing table
    @PutMapping("/{tableId}/update")
    public ResponseEntity<String> updateTable(@PathVariable int tableId,
                                              @RequestParam int capacity,
                                              @RequestParam String status) {
        boolean success = tableService.updateTable(tableId, capacity, status);
        if (success) {
            return new ResponseEntity<>("Table updated successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update table.", HttpStatus.BAD_REQUEST);
    }

    // Delete a table
    @DeleteMapping("/{tableId}/delete")
    public ResponseEntity<String> deleteTable(@PathVariable int tableId) {
        try {
            tableService.deleteTable(tableId);
            return new ResponseEntity<>("Table deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete table: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package com.example.fullstack_backend.services;

import com.example.fullstack_backend.model.RestaurantTable;
import com.example.fullstack_backend.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public List<RestaurantTable> getTablesByRestaurantId(int restaurantId) {
        return tableRepository.findTablesByRestaurantId(restaurantId);
    }

    public void addTable(int restaurantId, int seats) {
        tableRepository.addTable(restaurantId, seats);
    }

    public boolean updateTable(int tableId, int capacity, String status) {
        return tableRepository.updateTable(tableId, capacity, status) > 0;
    }

    public boolean deleteTable(int tableId) {
        tableRepository.deleteTable(tableId);
        return true;
    }
}

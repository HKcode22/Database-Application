package com.example.restaurantreservation.services;

import com.example.restaurantreservation.models.Tables;
import com.example.restaurantreservation.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public List<Tables> getTablesByRestaurantId(int restaurantId) {
        return tableRepository.findTablesByRestaurantId(restaurantId);
    }

    public void addTable(int restaurantId, int seats) {
        tableRepository.addTable(restaurantId, seats);
    }

    public boolean updateTable(int tableId, int seats) {
        return tableRepository.updateTable(tableId, seats) > 0;
    }

    public boolean deleteTable(int tableId) {
        tableRepository.deleteTable(tableId);
        return true;
    }
}

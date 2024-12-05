package main.java.com.example.restaurantreservation.repositories;

import main.java.com.example.restaurantreservation.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    // Fetch all restaurants using native SQL
    @Query(value = "SELECT * FROM Restaurants", nativeQuery = true)
    List<Restaurant> findAllRestaurants();

    // Get restaurant by ID using native SQL
    @Query(value = "SELECT * FROM Restaurants WHERE restaurant_id = :id", nativeQuery = true)
    Restaurant findRestaurantById(int id);

    // Add new restaurant using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Restaurants (name, street_address, city, state, zip_code, category_id) VALUES (:name, :streetaddress, :city, :state, :zip_code, :categoryId)", nativeQuery = true)
    void addRestaurant(String name, String streetAddress, int categoryId);

    // Update restaurant using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE Restaurants SET name = :name, street_address = :streetAddress, city = :city, state = :state, zip_code = :zip_code WHERE restaurant_id = :restaurantId", nativeQuery = true)
    int updateRestaurant(int restaurantId, String name, String streetAddress, String city, String state, String zip_code);

    // Delete restaurant using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Restaurants WHERE restaurant_id = :restaurantId", nativeQuery = true)
    void deleteRestaurant(int restaurantId);
}

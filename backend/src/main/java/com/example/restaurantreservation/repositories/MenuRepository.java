package main.java.com.example.restaurantreservation.repositories;

import main.java.com.example.restaurantreservation.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    // Add a new menu item
    @Transactional
    @Query(value = "INSERT INTO Menus (restaurant_id, item_name, item_price, description) " +
            "VALUES (:restaurantId, :itemName, :itemPrice, :description)", nativeQuery = true)
    void addMenuItem(@Param("restaurantId") int restaurantId,
                     @Param("itemName") String itemName,
                     @Param("itemPrice") double itemPrice,
                     @Param("description") String description);

    // Update an existing menu item
    @Transactional
    @Query(value = "UPDATE Menus SET item_name = :itemName, item_price = :itemPrice, description = :description " +
            "WHERE menu_id = :menuId", nativeQuery = true)
    int updateMenuItem(@Param("menuId") int menuId,
                       @Param("itemName") String itemName,
                       @Param("itemPrice") double itemPrice,
                       @Param("description") String description);

    // Delete a menu item
    @Transactional
    @Query(value = "DELETE FROM Menus WHERE menu_id = :menuId", nativeQuery = true)
    void deleteMenuItem(@Param("menuId") int menuId);
}

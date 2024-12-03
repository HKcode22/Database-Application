package main.java.com.example.restaurantreservation.repositories;

import main.java.com.example.restaurantreservation.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // Fetch all categories using native SQL
    @Query(value = "SELECT * FROM category", nativeQuery = true)
    List<Category> findAllCategories();

    // Get category by ID using native SQL
    @Query(value = "SELECT * FROM category WHERE category_id = :id", nativeQuery = true)
    Category findCategoryById(int id);

    // Add new category using native SQL
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO category (category_name) VALUES (:categoryName)", nativeQuery = true)
    void addCategory(String categoryName);

    // Update category using native SQL
    @Modifying
    @Transactional
    @Query(value = "UPDATE category SET category_name = :categoryName WHERE category_id = :categoryId", nativeQuery = true)
    int updateCategory(int categoryId, String categoryName);

    // Delete category using native SQL
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM category WHERE category_id = :categoryId", nativeQuery = true)
    void deleteCategory(int categoryId);
}

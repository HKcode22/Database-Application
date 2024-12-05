package com.example.restaurantreservation.services;

import com.example.restaurantreservation.models.Category;
import com.example.restaurantreservation.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Fetch all categories using SQL
    public List<Category> getAllCategories() {
        return categoryRepository.findAllCategories();
    }

    // Get category by ID using SQL
    public Category getCategoryById(int id) {
        return categoryRepository.findCategoryById(id);
    }

    // Add new category using SQL
    public Category addCategory(Category category) {
        categoryRepository.addCategory(category.getCategoryName());
        return category;
    }

    // Update category using SQL
    public boolean updateCategory(int id, Category updatedCategory) {
        return categoryRepository.updateCategory(id, updatedCategory.getCategoryName()) > 0;
    }

    // Delete category using SQL
    public boolean deleteCategory(int id) {
        categoryRepository.deleteCategory(id);
        return true;
    }
}

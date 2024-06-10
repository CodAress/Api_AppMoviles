package upc.edu.LoggyAPI.product.service;

import upc.edu.LoggyAPI.product.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Long category_id, Category category);
    boolean deleteCategory(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
}

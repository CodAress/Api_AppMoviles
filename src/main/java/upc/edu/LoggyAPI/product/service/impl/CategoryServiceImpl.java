package upc.edu.LoggyAPI.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.product.model.Category;
import upc.edu.LoggyAPI.product.repository.CategoryRepository;
import upc.edu.LoggyAPI.product.service.CategoryService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        validateCategory(category);
        existCategoryByName(category);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long category_id, Category category) {
        existCategoryById(category_id);
        validateCategory(category);
        existCategoryByName(category);
        Category categoryToUpdate = categoryRepository.findById(category_id).get();
        categoryToUpdate.setName(category.getName());
        categoryToUpdate.setDescription(category.getDescription());
        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public boolean deleteCategory(Long id) {
        existCategoryById(id);
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Category getCategoryById(Long id) {
        existCategoryById(id);
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Unregistered categories");
        }
        return categories;
    }

    private void validateCategory(Category category){
        if(category.getName() == null || category.getName().isEmpty()){
            throw new IllegalArgumentException("Category name is required");
        }
    }

    private void existCategoryByName(Category category){
        if(categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new IllegalArgumentException("Category name already exists");
        }
    }

    private void existCategoryById(Long category_id){
        if(!categoryRepository.existsById(category_id)){
            throw new ResourceNotFoundException(String.format("Category with id %s not found", category_id));
        }
    }
}

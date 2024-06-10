package upc.edu.LoggyAPI.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.product.model.Category;
import upc.edu.LoggyAPI.product.model.Product;
import upc.edu.LoggyAPI.product.repository.CategoryRepository;
import upc.edu.LoggyAPI.product.repository.ProductRepository;
import upc.edu.LoggyAPI.product.service.ProductCategoryService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.Set;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product addCategoryToProductById(Long productId, Long category_id) {
        existProductById(productId);
        existCategoryById(category_id);
        Product product = productRepository.findById(productId).get();
        Category category = categoryRepository.findById(category_id).get();
        product.getCategories().add(category);
        return productRepository.save(product);
    }

    @Override
    public Product quitCategoryToProductById(Long product_id, Long category_id) {
        existProductById(product_id);
        existCategoryById(category_id);
        Product product = productRepository.findById(product_id).get();
        Category category = categoryRepository.findById(category_id).get();
        product.getCategories().remove(category);
        return productRepository.save(product);
    }

    @Override
    public Product quitAllCategories(Long product_id) {
        existProductById(product_id);
        Product product = productRepository.findById(product_id).get();
        product.getCategories().clear();
        return productRepository.save(product);
    }

    @Override
    public Set<Category> getAllCategoriesToProductById(Long product_id) {
        existProductById(product_id);
        Product product = productRepository.findById(product_id).get();
        return product.getCategories();
    }

    @Override
    public Set<Product> getAllProductsToCategoryById(Long category_id) {
        existCategoryById(category_id);
        Category category = categoryRepository.findById(category_id).get();
        return category.getProducts();
    }

    private void existProductById(Long productId){
        if(!productRepository.existsById(productId)){
            throw new ResourceNotFoundException(String.format("Product with id %s not found", productId));
        }
    }

    private void existCategoryById(Long categoryId){
        if(!categoryRepository.existsById(categoryId)){
            throw new ResourceNotFoundException(String.format("Category with id %s not found", categoryId));
        }
    }
}

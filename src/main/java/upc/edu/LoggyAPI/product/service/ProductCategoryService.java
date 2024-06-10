package upc.edu.LoggyAPI.product.service;

import upc.edu.LoggyAPI.product.model.Category;
import upc.edu.LoggyAPI.product.model.Product;

import java.util.Set;

public interface ProductCategoryService {
    Product addCategoryToProductById(Long productId, Long category_id);
    Product quitCategoryToProductById(Long product_id, Long category_id);
    Product quitAllCategories(Long product_id);
    Set<Category> getAllCategoriesToProductById(Long product_id);
    Set<Product> getAllProductsToCategoryById(Long category_id);

}

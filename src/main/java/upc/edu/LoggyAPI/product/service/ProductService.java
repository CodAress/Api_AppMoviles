package upc.edu.LoggyAPI.product.service;

import upc.edu.LoggyAPI.product.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Long brand_id, Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByBrand(Long brand_id);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);
}

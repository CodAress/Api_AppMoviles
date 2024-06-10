package upc.edu.LoggyAPI.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.brand.repository.BrandRepository;
import upc.edu.LoggyAPI.product.model.Product;
import upc.edu.LoggyAPI.product.repository.ProductRepository;
import upc.edu.LoggyAPI.product.service.ProductService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Product createProduct(Long brand_id,Product product) {
        existBrandById(brand_id);
        validateProduct(product);
        existProductByCodename(product);
        product.setBrand(brandRepository.findById(brand_id).get());
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        existProductById(id);
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Unregistered products");
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        existProductById(id);
        validateProduct(product);
        Product productToUpdate = productRepository.findById(id).get();
        productToUpdate.setCodename(product.getCodename());
        productToUpdate.setImage(product.getImage());
        return productRepository.save(productToUpdate);
    }

    @Override
    public boolean deleteProduct(Long id) {
        existProductById(id);
        productRepository.deleteById(id);
        return true;
    }

    private void validateProduct(Product product) {
        if (product.getCodename() == null || product.getCodename().isEmpty()) {
            throw new IllegalArgumentException("Product codename is required");
        }
        if (product.getImage() == null || product.getImage().isEmpty()) {
            throw new IllegalArgumentException("Product image is required");
        }
    }

    private void existProductByCodename(Product product){
        if (productRepository.existsByCodenameIgnoreCase(product.getCodename())) {
            throw new IllegalArgumentException("Product codename already exists");
        }
    }

    private void existProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Product with id %s not found", id));
        }
    }

    private void existBrandById(Long brand_id) {
        if (!brandRepository.existsById(brand_id)) {
            throw new ResourceNotFoundException(String.format("Brand with id %s not found", brand_id));
        }
    }
}

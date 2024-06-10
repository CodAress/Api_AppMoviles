package upc.edu.LoggyAPI.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.product.model.Description;
import upc.edu.LoggyAPI.product.model.Product;
import upc.edu.LoggyAPI.product.repository.DescriptionRepository;
import upc.edu.LoggyAPI.product.repository.ProductRepository;
import upc.edu.LoggyAPI.product.service.ProductDescriptionService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.Set;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DescriptionRepository descriptionRepository;

    @Override
    public Product addDescriptionToProductById(Long productId, Long description_id) {
        existProductById(productId);
        existDescriptionById(description_id);
        Product product = productRepository.findById(productId).get();
        Description description = descriptionRepository.findById(description_id).get();
        product.getDescriptions().add(description);
        return productRepository.save(product);
    }

    @Override
    public Product quitDescriptionToProductById(Long product_id, Long description_id) {
        existProductById(product_id);
        existDescriptionById(description_id);
        Product product = productRepository.findById(product_id).get();
        Description description = descriptionRepository.findById(description_id).get();
        product.getDescriptions().remove(description);
        return productRepository.save(product);
    }

    @Override
    public Product quitAllDescriptions(Long product_id) {
        existProductById(product_id);
        Product product = productRepository.findById(product_id).get();
        product.getDescriptions().clear();
        return productRepository.save(product);
    }

    @Override
    public Set<Description> getAllDescriptionsToProductById(Long product_id) {
        existProductById(product_id);
        Product product = productRepository.findById(product_id).get();
        return product.getDescriptions();
    }

    private void existProductById(Long productId){
        if(!productRepository.existsById(productId)){
            throw new ResourceNotFoundException(String.format("Product with id %s not found", productId));
        }
    }
    private void existDescriptionById(Long description_id){
        if(!descriptionRepository.existsById(description_id)){
            throw new ResourceNotFoundException(String.format("Description with id %s not found", description_id));
        }
    }
}

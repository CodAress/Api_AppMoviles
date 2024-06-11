package upc.edu.LoggyAPI.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.product.model.Product;
import upc.edu.LoggyAPI.product.model.Specification;
import upc.edu.LoggyAPI.product.repository.ProductRepository;
import upc.edu.LoggyAPI.product.repository.SpecificationRepository;
import upc.edu.LoggyAPI.product.service.ProductSpecificationService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.Set;

@Service
public class ProductSpecificationServiceImpl implements ProductSpecificationService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SpecificationRepository specificationRepository;

    @Override
    public Product addSpecificationToProductById(Long product_id, Long specification_id) {
        existProductById(product_id);
        existSpecificationById(specification_id);
        Specification specification = specificationRepository.findById(specification_id).get();
        Product product = productRepository.findById(product_id).get();
        existSpecificationInProduct(specification, product);
        product.getSpecifications().add(specification);
        return productRepository.save(product);
    }

    @Override
    public Product quitSpecificationToProductById(Long product_id, Long specification_id) {
        existProductById(product_id);
        existSpecificationById(specification_id);
        Specification specification = specificationRepository.findById(specification_id).get();
        Product product = productRepository.findById(product_id).get();
        dontExistSpecificationInProduct(specification, product);
        product.getSpecifications().remove(specification);
        return productRepository.save(product);
    }

    @Override
    public Product quitAllSpecifications(Long product_id) {
        existSpecificationById(product_id);
        Product product = productRepository.findById(product_id).get();
        dontExistsSpecificationsInProduct(product);
        product.getSpecifications().clear();
        return productRepository.save(product);
    }

    @Override
    public Set<Specification> getAllSpecificationsToProductById(Long product_id) {
        existProductById(product_id);
        Product product = productRepository.findById(product_id).get();
        dontExistsSpecificationsInProduct(product);
        return product.getSpecifications();
    }

    private void existProductById(Long product_id){
        if(productRepository.existsById(product_id)){
            throw new ResourceNotFoundException(String.format("Product with id %s not found", product_id));
        }
    }

    private void existSpecificationById(Long specification_id){
        if(specificationRepository.existsById(specification_id)){
            throw new ResourceNotFoundException(String.format("Specification with id %s not found", specification_id));
        }
    }

    private void existSpecificationInProduct(Specification specification, Product product){
        if(product.getSpecifications().contains(specification)){
            throw new ResourceNotFoundException(String.format("Specification with id %s already exists in product with id %s", specification.getId(), product.getId()));
        }
    }

    private void dontExistSpecificationInProduct(Specification specification, Product product){
        if(!product.getSpecifications().contains(specification)){
            throw new ResourceNotFoundException(String.format("Specification with id %s not exists in product with id %s", specification.getId(), product.getId()));
        }
    }
    private void dontExistsSpecificationsInProduct(Product product){
        if(product.getSpecifications().isEmpty()){
            throw new ResourceNotFoundException(String.format("Product with id %s not have specifications", product.getId()));
        }
    }
}

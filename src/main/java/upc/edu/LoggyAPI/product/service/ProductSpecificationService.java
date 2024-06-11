package upc.edu.LoggyAPI.product.service;

import upc.edu.LoggyAPI.product.model.Product;
import upc.edu.LoggyAPI.product.model.Specification;

import java.util.Set;

public interface ProductSpecificationService {
    Product addSpecificationToProductById(Long product_id, Long specification_id);
    Product quitSpecificationToProductById(Long product_id, Long specification_id);
    Product quitAllSpecifications(Long product_id);
    Set<Specification> getAllSpecificationsToProductById(Long product_id);
}

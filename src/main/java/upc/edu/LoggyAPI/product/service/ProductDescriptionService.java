package upc.edu.LoggyAPI.product.service;

import upc.edu.LoggyAPI.product.model.Description;
import upc.edu.LoggyAPI.product.model.Product;

import java.util.Set;

public interface ProductDescriptionService {
    Product addDescriptionToProductById(Long productId, Long description_id);
    Product quitDescriptionToProductById(Long product_id, Long description_id);
    Product quitAllDescriptions(Long product_id);;
    Set<Description> getAllDescriptionsToProductById(Long product_id);
}

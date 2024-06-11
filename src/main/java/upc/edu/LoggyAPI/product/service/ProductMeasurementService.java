package upc.edu.LoggyAPI.product.service;

import upc.edu.LoggyAPI.product.model.Measurement;
import upc.edu.LoggyAPI.product.model.Product;

import java.util.Set;

public interface ProductMeasurementService {
    Product addMeasurementToProductById(Long product_id, Long measurement_id);
    Product quitMeasurementToProductById(Long product_id, Long measurement_id);
    Product quitAllMeasurements(Long product_id);
    Set<Measurement> getAllMeasurementsToProductById(Long product_id);
}

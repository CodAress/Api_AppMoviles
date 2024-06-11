package upc.edu.LoggyAPI.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.product.dto.MeasurementResponse;
import upc.edu.LoggyAPI.product.dto.mapper.MeasurementMapper;
import upc.edu.LoggyAPI.product.model.Measurement;
import upc.edu.LoggyAPI.product.model.Product;
import upc.edu.LoggyAPI.product.repository.MeasurementRepository;
import upc.edu.LoggyAPI.product.repository.ProductRepository;
import upc.edu.LoggyAPI.product.service.ProductMeasurementService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

@Service
public class ProductMeasurementServiceImpl implements ProductMeasurementService {
    @Autowired
    private MeasurementRepository measurementRepository;
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product addMeasurementToProductById(Long product_id, Long measurement_id) {
        existProductById(product_id);
        existMeasurementById(measurement_id);
        Measurement measurement = measurementRepository.findById(measurement_id).get();
        Product product = productRepository.findById(product_id).get();
        product.getMeasurements().add(measurement);
        return productRepository.save(product);
    }

    @Override
    public Product quitMeasurementToProductById(Long product_id, Long measurement_id) {
        existMeasurementById(measurement_id);
        existProductById(product_id);
        Measurement measurement = measurementRepository.findById(measurement_id).get();
        Product product = productRepository.findById(product_id).get();
        product.getMeasurements().remove(measurement);
        return productRepository.save(product);
    }

    @Override
    public Product quitAllMeasurements(Long product_id) {
        existProductById(product_id);
        Product product = productRepository.findById(product_id).get();
        product.getMeasurements().clear();
        return productRepository.save(product);
    }

    @Override
    public Set<Measurement> getAllMeasurementsToProductById(Long product_id) {
        existProductById(product_id);
        Product product = productRepository.findById(product_id).get();
        return product.getMeasurements();
    }
    private void existProductById(Long product_id){
        if(productRepository.existsById(product_id)){
            throw new ResourceNotFoundException(String.format("Product by id %s not found", product_id));
        }
    }
    private void existMeasurementById(Long measurement_id){
        if(measurementRepository.existsById(measurement_id)){
            throw new ResourceNotFoundException(String.format("Measurement by id %s not found", measurement_id));
        }
    }
}

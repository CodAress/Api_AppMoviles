package upc.edu.LoggyAPI.product.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.MeasurementResponse;
import upc.edu.LoggyAPI.product.dto.ProductResponse;
import upc.edu.LoggyAPI.product.dto.mapper.MeasurementMapper;
import upc.edu.LoggyAPI.product.dto.mapper.ProductMapper;
import upc.edu.LoggyAPI.product.service.ProductMeasurementService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductMeasurementController {
    @Autowired
    private ProductMeasurementService productMeasurementService;
    @Transactional
    @PostMapping("/products/{product_id}/measurements/{measurement_id}")
    public ResponseEntity<ProductResponse> addMeasurementToProductById(@PathVariable("product_id") Long product_id, @PathVariable("measurement_id") Long measurement_id){
        var product = productMeasurementService.addMeasurementToProductById(product_id,measurement_id);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("product/{product_id}/measurements/{measurement_id}")
    public ResponseEntity<ProductResponse> quitMeasurementToProductById(@PathVariable("product_id") Long product_id, @PathVariable("measurement_id") Long measurement_id){
        var product = productMeasurementService.quitMeasurementToProductById(product_id,measurement_id);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/products/{productId}/measurements")
    public ResponseEntity<ProductResponse> quitAllMeasurements(@PathVariable("productId") Long productId){
        var product = productMeasurementService.quitAllMeasurements(productId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/products/{productId}/descriptions")
    public ResponseEntity<List<MeasurementResponse>> getAllMeasurementsToProductById(@PathVariable("productId") Long productId){
        var measurements = productMeasurementService.getAllMeasurementsToProductById(productId);
        var measurementsResponse = MeasurementMapper.INSTANCE.measurementsToMeasurementsResponses(measurements.stream().toList());
        return new ResponseEntity<List<MeasurementResponse>>(measurementsResponse, HttpStatus.OK);
    }
}

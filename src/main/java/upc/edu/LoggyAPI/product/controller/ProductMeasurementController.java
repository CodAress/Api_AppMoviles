package upc.edu.LoggyAPI.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Gestión de Medidas de Productos", description = "Controlador para operaciones relacionadas con medidas de productos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductMeasurementController {
    @Autowired
    private ProductMeasurementService productMeasurementService;

    @Operation(summary = "Añade una medida a un producto por ID", description = "Este endpoint añade una medida a un producto y devuelve los detalles del producto con la medida añadida.")
    @Transactional
    @PostMapping("/products/{product_id}/measurements/{measurement_id}")
    public ResponseEntity<ProductResponse> addMeasurementToProductById(@PathVariable("product_id") Long product_id, @PathVariable("measurement_id") Long measurement_id){
        var product = productMeasurementService.addMeasurementToProductById(product_id,measurement_id);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Quita una medida de un producto por ID", description = "Este endpoint quita una medida de un producto y devuelve los detalles del producto con la medida quitada.")
    @Transactional
    @DeleteMapping("/products/{product_id}/measurements/{measurement_id}")
    public ResponseEntity<ProductResponse> quitMeasurementToProductById(@PathVariable("product_id") Long product_id, @PathVariable("measurement_id") Long measurement_id){
        var product = productMeasurementService.quitMeasurementToProductById(product_id,measurement_id);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Quita todas las medidas de un producto por ID", description = "Este endpoint quita todas las medidas de un producto y devuelve los detalles del producto con las medidas quitadas.")
    @Transactional
    @DeleteMapping("/products/{product_id}/measurements")
    public ResponseEntity<ProductResponse> quitAllMeasurements(@PathVariable("product_id") Long productId){
        var product = productMeasurementService.quitAllMeasurements(productId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todas las medidas de un producto por ID", description = "Este endpoint devuelve los detalles de todas las medidas de un producto por su id.")
    @Transactional
    @GetMapping("/products/{productId}/measurements")
    public ResponseEntity<List<MeasurementResponse>> getAllMeasurementsToProductById(@PathVariable("productId") Long productId){
        var measurements = productMeasurementService.getAllMeasurementsToProductById(productId);
        var measurementsResponse = MeasurementMapper.INSTANCE.measurementsToMeasurementsResponses(measurements.stream().toList());
        return new ResponseEntity<List<MeasurementResponse>>(measurementsResponse, HttpStatus.OK);
    }
}

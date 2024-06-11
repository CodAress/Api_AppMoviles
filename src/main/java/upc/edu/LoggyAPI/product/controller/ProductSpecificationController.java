package upc.edu.LoggyAPI.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.ProductResponse;
import upc.edu.LoggyAPI.product.dto.SpecificationResponse;
import upc.edu.LoggyAPI.product.dto.mapper.ProductMapper;
import upc.edu.LoggyAPI.product.dto.mapper.SpecificationMapper;
import upc.edu.LoggyAPI.product.service.ProductSpecificationService;

import java.util.List;

@Tag(name = "Gestión de Especificaciones de Productos", description = "Controlador para operaciones relacionadas con especificaciones de productos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductSpecificationController {
    @Autowired
    private ProductSpecificationService productSpecificationService;

    @Operation(summary = "Añade una especificación a un producto por ID", description = "Este endpoint añade una especificación a un producto y devuelve los detalles del producto con la especificación añadida.")
    @Transactional
    @PostMapping("/products/{product_id}/specifications/{specification_id}")
    public ResponseEntity<ProductResponse> addSpecificationToProductById(@PathVariable("product_id") Long product_id, @PathVariable("specification_id") Long specification_id){
        var product = productSpecificationService.addSpecificationToProductById(product_id,specification_id);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Quita una especificación de un producto por ID", description = "Este endpoint quita una especificación de un producto y devuelve los detalles del producto con la especificación quitada.")
    @Transactional
    @DeleteMapping("/products/{product_id}/specifications/{specification_id}")
    public ResponseEntity<ProductResponse> quitSpecificationToProductById(@PathVariable("product_id") Long product_id, @PathVariable("specification_id") Long specification_id){
        var product = productSpecificationService.quitSpecificationToProductById(product_id,specification_id);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Quita todas las especificaciones de un producto por ID", description = "Este endpoint quita todas las especificaciones de un producto y devuelve los detalles del producto con las especificaciones quitadas.")
    @Transactional
    @DeleteMapping("/products/{product_id}/specifications")
    public ResponseEntity<ProductResponse> quitAllSpecifications(@PathVariable("product_id") Long productId){
        var product = productSpecificationService.quitAllSpecifications(productId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todas las especificaciones de un producto por ID", description = "Este endpoint devuelve los detalles de todas las especificaciones de un producto por su id.")
    @Transactional
    @GetMapping("/products/{productId}/specifications")
    public ResponseEntity<List<SpecificationResponse>> getAllSpecificationsToProductById(@PathVariable("productId") Long productId){
        var specifications = productSpecificationService.getAllSpecificationsToProductById(productId);
        var specificationsResponse = SpecificationMapper.INSTANCE.specificationsToSpecificationResponses(specifications.stream().toList());
        return new ResponseEntity<List<SpecificationResponse>>(specificationsResponse, HttpStatus.OK);
    }
}

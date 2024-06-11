package upc.edu.LoggyAPI.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.DescriptionResponse;
import upc.edu.LoggyAPI.product.dto.ProductResponse;
import upc.edu.LoggyAPI.product.dto.mapper.DescriptionMapper;
import upc.edu.LoggyAPI.product.dto.mapper.ProductMapper;
import upc.edu.LoggyAPI.product.service.ProductDescriptionService;

import java.util.List;
@Tag(name = "Gestión de Descripciones de Productos", description = "Controlador para operaciones relacionadas con descripciones de productos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductDescriptionController {
    @Autowired
    private ProductDescriptionService productDescriptionService;

    @Operation(summary = "Añade una descripción a un producto por ID", description = "Este endpoint añade una descripción a un producto y devuelve los detalles del producto con la descripción añadida.")
    @Transactional
    @PostMapping("/products/{productId}/descriptions/{descriptionId}")
    public ResponseEntity<ProductResponse> addDescriptionToProductById(@PathVariable("productId") Long productId, @PathVariable("descriptionId") Long descriptionId){
        var product = productDescriptionService.addDescriptionToProductById(productId, descriptionId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Quita una descripción de un producto por ID", description = "Este endpoint quita una descripción de un producto y devuelve los detalles del producto con la descripción quitada.")
    @Transactional
    @DeleteMapping("/products/{productId}/descriptions/{descriptionId}")
    public ResponseEntity<ProductResponse> quitDescriptionToProductById(@PathVariable("productId") Long productId, @PathVariable("descriptionId") Long descriptionId){
        var product = productDescriptionService.quitDescriptionToProductById(productId, descriptionId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Quita todas las descripciones de un producto por ID", description = "Este endpoint quita todas las descripciones de un producto y devuelve los detalles del producto con las descripciones quitadas.")
    @Transactional
    @DeleteMapping("/products/{productId}/descriptions")
    public ResponseEntity<ProductResponse> quitAllDescriptions(@PathVariable("productId") Long productId){
        var product = productDescriptionService.quitAllDescriptions(productId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todas las descripciones de un producto por ID", description = "Este endpoint devuelve los detalles de todas las descripciones de un producto por su id.")
    @Transactional
    @GetMapping("/products/{productId}/descriptions")
    public ResponseEntity<List<DescriptionResponse>> getAllDescriptionsToProductById(@PathVariable("productId") Long productId){
        var descriptions = productDescriptionService.getAllDescriptionsToProductById(productId);
        var descriptionsResponse = DescriptionMapper.INSTANCE.descriptionsToDescriptionResponses(descriptions.stream().toList());
        return new ResponseEntity<List<DescriptionResponse>>(descriptionsResponse, HttpStatus.OK);
    }
}

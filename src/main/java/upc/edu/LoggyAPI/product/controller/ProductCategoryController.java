package upc.edu.LoggyAPI.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.CategoryResponse;
import upc.edu.LoggyAPI.product.dto.ProductResponse;
import upc.edu.LoggyAPI.product.dto.mapper.CategoryMapper;
import upc.edu.LoggyAPI.product.dto.mapper.ProductMapper;
import upc.edu.LoggyAPI.product.service.ProductCategoryService;

import java.util.List;

@Tag(name = "Gestión de Categorías de Productos", description = "Controlador para operaciones relacionadas con categorías de productos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Operation(summary = "Añade una categoría a un producto por ID", description = "Este endpoint añade una categoría a un producto y devuelve los detalles del producto con la categoría añadida.")
    @Transactional
    @PostMapping("/products/{productId}/categories/{categoryId}")
    public ResponseEntity<ProductResponse> addCategoryToProductById(@PathVariable("productId") Long productId, @PathVariable("categoryId") Long categoryId){
        var product = productCategoryService.addCategoryToProductById(productId, categoryId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Quita una categoría de un producto por ID", description = "Este endpoint quita una categoría de un producto y devuelve los detalles del producto con la categoría quitada.")
    @Transactional
    @DeleteMapping("/products/{productId}/categories/{categoryId}")
    public ResponseEntity<ProductResponse> quitCategoryToProductById(@PathVariable("productId") Long productId, @PathVariable("categoryId") Long categoryId){
        var product = productCategoryService.quitCategoryToProductById(productId, categoryId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Quita todas las categorías de un producto por ID", description = "Este endpoint quita todas las categorías de un producto y devuelve los detalles del producto con las categorías quitadas.")
    @Transactional
    @DeleteMapping("/products/{productId}/categories")
    public ResponseEntity<ProductResponse> quitAllCategories(@PathVariable("productId") Long productId){
        var product = productCategoryService.quitAllCategories(productId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todas las categorías de un producto por ID", description = "Este endpoint devuelve los detalles de todas las categorías de un producto por su id.")
    @Transactional
    @GetMapping("/products/{productId}/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategoriesToProductById(@PathVariable("productId") Long productId){
        var categories = productCategoryService.getAllCategoriesToProductById(productId);
        var categoriesResponse = CategoryMapper.INSTANCE.categoriesToCategoryResponses(categories.stream().toList());
        return new ResponseEntity<List<CategoryResponse>>(categoriesResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todas las categorías de un producto por ID", description = "Este endpoint devuelve los detalles de todas las categorías de un producto por su id.")
    @Transactional
    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<ProductResponse>> getAllProductsToCategoryById(@PathVariable("categoryId") Long categoryId){
        var products = productCategoryService.getAllProductsToCategoryById(categoryId);
        var productsResponse = ProductMapper.INSTANCE.productsToProductResponses(products.stream().toList());
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }
}

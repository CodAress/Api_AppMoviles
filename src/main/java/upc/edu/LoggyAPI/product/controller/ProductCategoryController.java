package upc.edu.LoggyAPI.product.controller;

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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Transactional
    @PostMapping("/products/{productId}/categories/{categoryId}")
    public ResponseEntity<ProductResponse> addCategoryToProductById(@PathVariable("productId") Long productId, @PathVariable("categoryId") Long categoryId){
        var product = productCategoryService.addCategoryToProductById(productId, categoryId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/products/{productId}/categories/{categoryId}")
    public ResponseEntity<ProductResponse> quitCategoryToProductById(@PathVariable("productId") Long productId, @PathVariable("categoryId") Long categoryId){
        var product = productCategoryService.quitCategoryToProductById(productId, categoryId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/products/{productId}/categories")
    public ResponseEntity<ProductResponse> quitAllCategories(@PathVariable("productId") Long productId){
        var product = productCategoryService.quitAllCategories(productId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/products/{productId}/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategoriesToProductById(@PathVariable("productId") Long productId){
        var categories = productCategoryService.getAllCategoriesToProductById(productId);
        var categoriesResponse = CategoryMapper.INSTANCE.categoriesToCategoryResponses(categories.stream().toList());
        return new ResponseEntity<List<CategoryResponse>>(categoriesResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<ProductResponse>> getAllProductsToCategoryById(@PathVariable("categoryId") Long categoryId){
        var products = productCategoryService.getAllProductsToCategoryById(categoryId);
        var productsResponse = ProductMapper.INSTANCE.productsToProductResponses(products.stream().toList());
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }
}

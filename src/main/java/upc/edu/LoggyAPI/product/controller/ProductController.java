package upc.edu.LoggyAPI.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.ProductRequest;
import upc.edu.LoggyAPI.product.dto.ProductResponse;
import upc.edu.LoggyAPI.product.dto.mapper.ProductMapper;
import upc.edu.LoggyAPI.product.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Transactional
    @PostMapping("/brands/{brand_id}/products")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable("brand_id") Long brand_id, @RequestBody ProductRequest productRequest){
        var product = ProductMapper.INSTANCE.productRequestToProduct(productRequest);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productService.createProduct(brand_id, product));
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id){
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productService.getProductById(id));
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        var productsResponse = ProductMapper.INSTANCE.productsToProductResponses(productService.getAllProducts());
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest){
        var product = ProductMapper.INSTANCE.productRequestToProduct(productRequest);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productService.updateProduct(id, product));
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long id){
        var valor = productService.deleteProduct(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

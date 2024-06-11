package upc.edu.LoggyAPI.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Gestión de Productos", description = "Controlador para operaciones relacionadas con productos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Crea un nuevo producto", description = "Este endpoint crea un nuevo producto y devuelve los detalles del producto creado.")
    @Transactional
    @PostMapping("/brands/{brand_id}/products")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable("brand_id") Long brand_id, @RequestBody ProductRequest productRequest){
        var product = ProductMapper.INSTANCE.productRequestToProduct(productRequest);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productService.createProduct(brand_id, product));
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un producto por su ID", description = "Este endpoint devuelve los detalles de un producto por su id.")
    @Transactional
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id){
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productService.getProductById(id));
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los productos", description = "Este endpoint devuelve los detalles de todos los productos.")
    @Transactional
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        var productsResponse = ProductMapper.INSTANCE.productsToProductResponses(productService.getAllProducts());
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene los productos de una marca por ID", description = "Este endpoint devuelve los detalles de los productos de una marca por su id.")
    @Transactional
    @GetMapping("/brands/{brand_id}/products")
    public ResponseEntity<List<ProductResponse>> getProductsByBrand(@PathVariable("brand_id") Long brand_id){
        var productsResponse = ProductMapper.INSTANCE.productsToProductResponses(productService.getProductsByBrand(brand_id));
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un producto por su ID", description = "Este endpoint actualiza un producto y devuelve los detalles del producto actualizado.")
    @Transactional
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest){
        var product = ProductMapper.INSTANCE.productRequestToProduct(productRequest);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productService.updateProduct(id, product));
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto por su ID", description = "Este endpoint elimina un producto y devuelve un booleano indicando si se eliminó o no.")
    @Transactional
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long id){
        var valor = productService.deleteProduct(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

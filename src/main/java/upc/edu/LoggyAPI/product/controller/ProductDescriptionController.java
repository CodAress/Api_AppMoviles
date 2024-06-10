package upc.edu.LoggyAPI.product.controller;

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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductDescriptionController {
    @Autowired
    private ProductDescriptionService productDescriptionService;

    @Transactional
    @PostMapping("/products/{productId}/descriptions/{descriptionId}")
    public ResponseEntity<ProductResponse> addDescriptionToProductById(@PathVariable("productId") Long productId, @PathVariable("descriptionId") Long descriptionId){
        var product = productDescriptionService.addDescriptionToProductById(productId, descriptionId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/products/{productId}/descriptions/{descriptionId}")
    public ResponseEntity<ProductResponse> quitDescriptionToProductById(@PathVariable("productId") Long productId, @PathVariable("descriptionId") Long descriptionId){
        var product = productDescriptionService.quitDescriptionToProductById(productId, descriptionId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/products/{productId}/descriptions")
    public ResponseEntity<ProductResponse> quitAllDescriptions(@PathVariable("productId") Long productId){
        var product = productDescriptionService.quitAllDescriptions(productId);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/products/{productId}/descriptions")
    public ResponseEntity<List<DescriptionResponse>> getAllDescriptionsToProductById(@PathVariable("productId") Long productId){
        var descriptions = productDescriptionService.getAllDescriptionsToProductById(productId);
        var descriptionsResponse = DescriptionMapper.INSTANCE.descriptionsToDescriptionResponses(descriptions.stream().toList());
        return new ResponseEntity<List<DescriptionResponse>>(descriptionsResponse, HttpStatus.OK);
    }
}

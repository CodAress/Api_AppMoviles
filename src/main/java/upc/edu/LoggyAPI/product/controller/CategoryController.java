package upc.edu.LoggyAPI.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.CategoryRequest;
import upc.edu.LoggyAPI.product.dto.CategoryResponse;
import upc.edu.LoggyAPI.product.dto.mapper.CategoryMapper;
import upc.edu.LoggyAPI.product.service.CategoryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Transactional
    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        var category = CategoryMapper.INSTANCE.categoryRequestToCategory(categoryRequest);
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryService.createCategory(category));
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") Long id) {
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryService.getCategoryById(id));
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        var categoriesResponse = CategoryMapper.INSTANCE.categoriesToCategoryResponses(categoryService.getAllCategories());
        return new ResponseEntity<List<CategoryResponse>>(categoriesResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequest categoryRequest) {
        var category = CategoryMapper.INSTANCE.categoryRequestToCategory(categoryRequest);
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryService.updateCategory(id, category));
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") Long id) {
        var valor = categoryService.deleteCategory(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

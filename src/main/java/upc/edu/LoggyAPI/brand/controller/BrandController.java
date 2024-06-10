package upc.edu.LoggyAPI.brand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.brand.dto.BrandRequest;
import upc.edu.LoggyAPI.brand.dto.BrandResponse;
import upc.edu.LoggyAPI.brand.dto.mapper.BrandMapper;
import upc.edu.LoggyAPI.brand.service.BrandService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v4")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @Transactional
    @PostMapping(value = "/brands")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest){
        var brand = BrandMapper.INSTANCE.brandRequestToBrand(brandRequest);
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandService.createBrand(brand));
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/brands/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable("id") Long id){
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandService.getBrandById(id));
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/brands")
    public ResponseEntity<List<BrandResponse>> getAllBrands(){
        var brandsResponse = BrandMapper.INSTANCE.brandsToBrandResponses(brandService.getAllBrands());
        return new ResponseEntity<List<BrandResponse>>(brandsResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/brands/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable("id") Long id, @RequestBody BrandRequest brandRequest){
        var brand = BrandMapper.INSTANCE.brandRequestToBrand(brandRequest);
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandService.updateBrand(id, brand));
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/brands/{id}")
    public ResponseEntity<Boolean> deleteBrand(@PathVariable("id") Long id){
        var valor = brandService.deleteBrand(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

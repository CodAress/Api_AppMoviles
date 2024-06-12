package upc.edu.LoggyAPI.brand.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Gestión de Marcas", description = "Controlador para operaciones relacionadas con marcas")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v4")
public class BrandController {

    @Autowired
    private BrandService brandService;
    @Operation(summary = "Crea una nueva marca", description = "Este endpoint crea una nueva marca y devuelve los detalles de la nueva marca creada.")
    @Transactional
    @PostMapping(value = "/brands")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest){
        var brand = BrandMapper.INSTANCE.brandRequestToBrand(brandRequest);
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandService.createBrand(brand));
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.CREATED);
    }
    @Operation(summary = "Obtiene una marca por su ID", description = "Este endpoint devuelve los detalles de una marca por su id.")
    @Transactional
    @GetMapping(value = "/brands/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable("id") Long id){
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandService.getBrandById(id));
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }
    @Operation(summary = "Obtiene todas las marcas", description = "Este endpoint devuelve los detalles de todas las marcas.")
    @Transactional
    @GetMapping(value = "/brands")
    public ResponseEntity<List<BrandResponse>> getAllBrands(){
        var brandsResponse = BrandMapper.INSTANCE.brandsToBrandResponses(brandService.getAllBrands());
        return new ResponseEntity<List<BrandResponse>>(brandsResponse, HttpStatus.OK);
    }
    @Operation(summary = "Actualiza una marca por su ID", description = "Este endpoint actualiza una marca y devuelve los detalles de la marca actualizada.")
    @Transactional
    @PutMapping(value = "/brands/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable("id") Long id, @RequestBody BrandRequest brandRequest){
        var brand = BrandMapper.INSTANCE.brandRequestToBrand(brandRequest);
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandService.updateBrand(id, brand));
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }
    @Operation(summary = "Elimina una marca por su ID", description = "Este endpoint elimina una marca y devuelve un booleano indicando si se eliminó o no.")
    @Transactional
    @DeleteMapping(value = "/brands/{id}")
    public ResponseEntity<Boolean> deleteBrand(@PathVariable("id") Long id){
        var valor = brandService.deleteBrand(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

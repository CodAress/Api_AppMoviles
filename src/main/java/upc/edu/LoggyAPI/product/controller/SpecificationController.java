package upc.edu.LoggyAPI.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.SpecificationRequest;
import upc.edu.LoggyAPI.product.dto.SpecificationResponse;
import upc.edu.LoggyAPI.product.dto.mapper.SpecificationMapper;
import upc.edu.LoggyAPI.product.service.SpecificationService;

import java.util.List;

@Tag(name = "Gestión de Especificaciones", description = "Controlador para operaciones relacionadas con especificaciones")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    @Operation(summary = "Crea una nueva especificación", description = "Este endpoint crea una nueva especificación y devuelve los detalles de la especificación creada.")
    @Transactional
    @PostMapping("/specifications")
    public ResponseEntity<SpecificationResponse> createSpecification(@RequestBody SpecificationRequest specificationRequest){
        var specification = SpecificationMapper.INSTANCE.specificationRequestToSpecification(specificationRequest);
        var specificationResponse = SpecificationMapper.INSTANCE.specificationToSpecificationResponse(specificationService.createSpecification(specification));
        return new ResponseEntity<SpecificationResponse>(specificationResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene una especificación por su ID", description = "Este endpoint devuelve los detalles de una especificación por su id.")
    @Transactional
    @GetMapping("/specifications/{id}")
    public ResponseEntity<SpecificationResponse> getSpecificationById(@PathVariable("id") Long id){
        var specificationResponse = SpecificationMapper.INSTANCE.specificationToSpecificationResponse(specificationService.getSpecificationById(id));
        return new ResponseEntity<SpecificationResponse>(specificationResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todas las especificaciones", description = "Este endpoint devuelve los detalles de todas las especificaciones.")
    @Transactional
    @GetMapping("/specifications")
    public ResponseEntity<List<SpecificationResponse>> getAllSpecifications(){
        var specificationsResponse = SpecificationMapper.INSTANCE.specificationsToSpecificationResponses(specificationService.getAllSpecifications());
        return new ResponseEntity<List<SpecificationResponse>>(specificationsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza una especificación por su ID", description = "Este endpoint actualiza una especificación y devuelve los detalles de la especificación actualizada.")
    @Transactional
    @PutMapping("/specifications/{id}")
    public ResponseEntity<SpecificationResponse> updateSpecification(@PathVariable("id") Long id, @RequestBody SpecificationRequest specificationRequest){
        var specification = SpecificationMapper.INSTANCE.specificationRequestToSpecification(specificationRequest);
        var specificationResponse = SpecificationMapper.INSTANCE.specificationToSpecificationResponse(specificationService.updateSpecification(id, specification));
        return new ResponseEntity<SpecificationResponse>(specificationResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una especificación por su ID", description = "Este endpoint elimina una especificación y devuelve un booleano indicando si se eliminó o no.")
    @Transactional
    @DeleteMapping("/specifications/{id}")
    public ResponseEntity<Boolean> deleteSpecification(@PathVariable("id") Long id){
        var valor = specificationService.deleteSpecification(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

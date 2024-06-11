package upc.edu.LoggyAPI.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.DescriptionRequest;
import upc.edu.LoggyAPI.product.dto.DescriptionResponse;
import upc.edu.LoggyAPI.product.dto.mapper.DescriptionMapper;
import upc.edu.LoggyAPI.product.service.DescriptionService;

import java.util.List;

@Tag(name = "Gestión de Descripciones", description = "Controlador para operaciones relacionadas con descripciones")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class DescriptionController {
    @Autowired
    private DescriptionService descriptionService;

    @Operation(summary = "Crea una nueva descripción", description = "Este endpoint crea una nueva descripción y devuelve los detalles de la descripción creada.")
    @Transactional
    @PostMapping("/descriptions")
    public ResponseEntity<DescriptionResponse> createDescription(@RequestBody DescriptionRequest descriptionRequest){
        var description = DescriptionMapper.INSTANCE.descriptionRequestToDescription(descriptionRequest);
        var descriptionResponse = DescriptionMapper.INSTANCE.descriptionToDescriptionResponse(descriptionService.createDescription(description));
        return new ResponseEntity<DescriptionResponse>(descriptionResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene una descripción por su ID", description = "Este endpoint devuelve los detalles de una descripción por su id.")
    @Transactional
    @GetMapping("/descriptions/{id}")
    public ResponseEntity<DescriptionResponse> getDescriptionById(@PathVariable("id") Long id){
        var descriptionResponse = DescriptionMapper.INSTANCE.descriptionToDescriptionResponse(descriptionService.getDescriptionById(id));
        return new ResponseEntity<DescriptionResponse>(descriptionResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todas las descripciones", description = "Este endpoint devuelve los detalles de todas las descripciones.")
    @Transactional
    @GetMapping("/descriptions")
    public ResponseEntity<List<DescriptionResponse>> getAllDescriptions(){
        var descriptionsResponse = DescriptionMapper.INSTANCE.descriptionsToDescriptionResponses(descriptionService.getAllDescriptions());
        return new ResponseEntity<List<DescriptionResponse>>(descriptionsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza una descripción por su ID", description = "Este endpoint actualiza una descripción y devuelve los detalles de la descripción actualizada.")
    @Transactional
    @PutMapping("/descriptions/{id}")
    public ResponseEntity<DescriptionResponse> updateDescription(@PathVariable("id") Long id, @RequestBody DescriptionRequest descriptionRequest){
        var description = DescriptionMapper.INSTANCE.descriptionRequestToDescription(descriptionRequest);
        var descriptionResponse = DescriptionMapper.INSTANCE.descriptionToDescriptionResponse(descriptionService.updateDescription(id, description));
        return new ResponseEntity<DescriptionResponse>(descriptionResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una descripción por su ID", description = "Este endpoint elimina una descripción y devuelve un booleano indicando si se eliminó o no.")
    @Transactional
    @DeleteMapping("/descriptions/{id}")
    public ResponseEntity<Boolean> deleteDescription(@PathVariable("id") Long id){
        var valor = descriptionService.deleteDescription(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

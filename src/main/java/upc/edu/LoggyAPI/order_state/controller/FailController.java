package upc.edu.LoggyAPI.order_state.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.order_state.dto.FailRequest;
import upc.edu.LoggyAPI.order_state.dto.FailResponse;
import upc.edu.LoggyAPI.order_state.dto.mapper.FailMapper;
import upc.edu.LoggyAPI.order_state.service.FailService;

import java.util.List;

@Tag(name = "Gestión de Fallos", description = "Controlador para operaciones relacionadas con categorías")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class FailController {
    @Autowired
    private FailService failService;

    @Operation(summary = "Crea un nuevo fallo", description = "Este endpoint crea un nuevo fallo y devuelve los detalles del nuevo fallo creado.")
    @PostMapping("/fails")
    public ResponseEntity<FailResponse> createFail(@RequestBody FailRequest failRequest){
        var fail = FailMapper.INSTANCE.failRequestToFail(failRequest);
        var failResponse = FailMapper.INSTANCE.failToFailResponse(failService.createFail(fail));
        return new ResponseEntity<FailResponse>(failResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un fallo por su ID", description = "Este endpoint devuelve los detalles de un fallo por su id.")
    @GetMapping("/fails/{id}")
    public ResponseEntity<FailResponse> getFailById(@PathVariable("id") Long id){
        var failResponse = FailMapper.INSTANCE.failToFailResponse(failService.getFailById(id));
        return new ResponseEntity<FailResponse>(failResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los fallos", description = "Este endpoint devuelve los detalles de todos los fallos.")
    @GetMapping("/fails")
    public ResponseEntity<List<FailResponse>> getAllFails(){
        var failsResponse = FailMapper.INSTANCE.failsToFailsResponse(failService.getAllFails());
        return new ResponseEntity<List<FailResponse>>(failsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un fallo por su ID", description = "Este endpoint actualiza un fallo y devuelve los detalles del fallo actualizado.")
    @PutMapping("/fails/{id}")
    public ResponseEntity<FailResponse> updateFail(@PathVariable("id") Long id, @RequestBody FailRequest failRequest){
        var fail = FailMapper.INSTANCE.failRequestToFail(failRequest);
        var failResponse = FailMapper.INSTANCE.failToFailResponse(failService.updteFail(id, fail));
        return new ResponseEntity<FailResponse>(failResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un fallo por su ID", description = "Este endpoint elimina un fallo y devuelve un booleano indicando si se eliminó o no.")
    @DeleteMapping("/fails/{id}")
    public ResponseEntity<Boolean> deleteFail(@PathVariable("id") Long id){
        return new ResponseEntity<Boolean>(failService.deleteFail(id), HttpStatus.OK);
    }

}

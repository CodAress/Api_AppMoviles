package upc.edu.LoggyAPI.order_state.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.order_state.dto.BatchRequest;
import upc.edu.LoggyAPI.order_state.dto.BatchResponse;
import upc.edu.LoggyAPI.order_state.dto.mapper.BatchMapper;
import upc.edu.LoggyAPI.order_state.service.BatchService;

import java.util.List;

@Tag(name = "Gestión de Lotes", description = "Controlador para operaciones relacionadas con lotes")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class BatchController {
    @Autowired
    private BatchService batchService;

    @Operation(summary = "Crea un nuevo lote", description = "Este endpoint crea un nuevo lote y devuelve los detalles del nuevo lote creado.")
    @PostMapping("/batches")
    public ResponseEntity<BatchResponse> createBatch(@RequestBody BatchRequest batchRequest){
        var batch = BatchMapper.INSTANCE.batchRequestToBatch(batchRequest);
        var batchResponse = BatchMapper.INSTANCE.batchToBatchResponse(batchService.createBatch(batch));
        return new ResponseEntity<BatchResponse>(batchResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un lote por su ID", description = "Este endpoint devuelve los detalles de un lote por su id.")
    @GetMapping("/batches/{id}")
    public ResponseEntity<BatchResponse> getBatchById(@PathVariable("id") Long id){
        var batchResponse = BatchMapper.INSTANCE.batchToBatchResponse(batchService.getBatchById(id));
        return new ResponseEntity<BatchResponse>(batchResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los lotes", description = "Este endpoint devuelve los detalles de todos los lotes.")
    @GetMapping("/batches")
    public ResponseEntity<List<BatchResponse>> getAllBatches(){
        var batchesResponse = BatchMapper.INSTANCE.batchesToBatchesResponse(batchService.getAllBatches());
        return new ResponseEntity<List<BatchResponse>>(batchesResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un lote por su ID", description = "Este endpoint actualiza un lote y devuelve los detalles del lote actualizado.")
    @PutMapping("/batches/{id}")
    public ResponseEntity<BatchResponse> updateBatch(@PathVariable("id") Long id, @RequestBody BatchRequest batchRequest){
        var batch = BatchMapper.INSTANCE.batchRequestToBatch(batchRequest);
        var batchResponse = BatchMapper.INSTANCE.batchToBatchResponse(batchService.updateBatch(id, batch));
        return new ResponseEntity<BatchResponse>(batchResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un lote por su ID", description = "Este endpoint elimina un lote y devuelve un booleano indicando si se eliminó o no.")
    @DeleteMapping("/batches/{id}")
    public ResponseEntity<Boolean> deleteBatch(@PathVariable("id") Long id){
        return new ResponseEntity<Boolean>(batchService.deleteBatch(id), HttpStatus.OK);
    }
}

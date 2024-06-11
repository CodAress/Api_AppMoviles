package upc.edu.LoggyAPI.turn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.turn.dto.TurnRequest;
import upc.edu.LoggyAPI.turn.dto.TurnResponse;
import upc.edu.LoggyAPI.turn.dto.mapper.TurnMapper;
import upc.edu.LoggyAPI.turn.service.TurnService;

import java.util.List;
@Tag(name = "Gestión de Turnos", description = "Controlador para operaciones relacionadas con turnos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class TurnController {
    @Autowired
    private TurnService turnService;

    @Operation(summary = "Crea un nuevo turno", description = "Este endpoint crea un nuevo turno y devuelve los detalles del turno creado.")
    @Transactional
    @PostMapping(value = "/turns")
    public ResponseEntity<TurnResponse> createTurn(@RequestBody TurnRequest turnRequest){
        var turn = TurnMapper.INSTANCE.turnRequestToTurn(turnRequest);
        var turnResponse = TurnMapper.INSTANCE.turnToTurnResponse(turnService.createTurn(turn));
        return new ResponseEntity<TurnResponse>(turnResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un turno por su ID", description = "Este endpoint devuelve los detalles de un turno por su id.")
    @Transactional
    @GetMapping(value = "/turns/{id}")
    public ResponseEntity<TurnResponse> getTurnById(@PathVariable("id") Long id){
        var turnResponse = TurnMapper.INSTANCE.turnToTurnResponse(turnService.getTurnById(id));
        return new ResponseEntity<TurnResponse>(turnResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los turnos", description = "Este endpoint devuelve los detalles de todos los turnos.")
    @Transactional
    @GetMapping(value = "/turns")
    public ResponseEntity<List<TurnResponse>> getAllTurns(){
        var turnsResponse = TurnMapper.INSTANCE.turnsToTurnsResponse(turnService.getAllTurns());
        return new ResponseEntity<List<TurnResponse>>(turnsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un turno por su ID", description = "Este endpoint actualiza un turno y devuelve los detalles del turno actualizado.")
    @Transactional
    @PutMapping(value = "/turns/{id}")
    public ResponseEntity<TurnResponse> updateTurn(@PathVariable("id") Long id, @RequestBody TurnRequest turnRequest){
        var turn = TurnMapper.INSTANCE.turnRequestToTurn(turnRequest);
        var turnResponse = TurnMapper.INSTANCE.turnToTurnResponse(turnService.updateTurn(id, turn));
        return new ResponseEntity<TurnResponse>(turnResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un turno por su ID", description = "Este endpoint elimina un turno y devuelve un booleano indicando si se eliminó o no.")
    @Transactional
    @DeleteMapping(value = "/turns/{id}")
    public ResponseEntity<Boolean> deleteTurn(@PathVariable("id") Long id){
        var valor = turnService.deleteTurn(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

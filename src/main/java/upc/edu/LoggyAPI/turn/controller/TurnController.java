package upc.edu.LoggyAPI.turn.controller;

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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class TurnController {
    @Autowired
    private TurnService turnService;

    @Transactional
    @PostMapping(value = "/turns")
    public ResponseEntity<TurnResponse> createTurn(@RequestBody TurnRequest turnRequest){
        var turn = TurnMapper.INSTANCE.turnRequestToTurn(turnRequest);
        var turnResponse = TurnMapper.INSTANCE.turnToTurnResponse(turnService.createTurn(turn));
        return new ResponseEntity<TurnResponse>(turnResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/turns/{id}")
    public ResponseEntity<TurnResponse> getTurnById(@PathVariable("id") Long id){
        var turnResponse = TurnMapper.INSTANCE.turnToTurnResponse(turnService.getTurnById(id));
        return new ResponseEntity<TurnResponse>(turnResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/turns")
    public ResponseEntity<List<TurnResponse>> getAllTurns(){
        var turnsResponse = TurnMapper.INSTANCE.turnsToTurnsResponse(turnService.getAllTurns());
        return new ResponseEntity<List<TurnResponse>>(turnsResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/turns/{id}")
    public ResponseEntity<TurnResponse> updateTurn(@PathVariable("id") Long id, @RequestBody TurnRequest turnRequest){
        var turn = TurnMapper.INSTANCE.turnRequestToTurn(turnRequest);
        var turnResponse = TurnMapper.INSTANCE.turnToTurnResponse(turnService.updateTurn(id, turn));
        return new ResponseEntity<TurnResponse>(turnResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/turns/{id}")
    public ResponseEntity<Boolean> deleteTurn(@PathVariable("id") Long id){
        var valor = turnService.deleteTurn(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

package upc.edu.LoggyAPI.turn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.turn.dto.TurnResponse;
import upc.edu.LoggyAPI.turn.dto.UserTurn;
import upc.edu.LoggyAPI.turn.service.UserTurnService;
import upc.edu.LoggyAPI.user.dto.UserResponse;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class UserTurnController {

    @Autowired
    private UserTurnService userTurnService;

    @Transactional
    @PostMapping(value = "/users/{id_user}/turns/{id_turn}")
    public ResponseEntity<UserTurn> addUserToTurn(@PathVariable("id_user") Long id_user, @PathVariable("id_turn") Long id_turn){
        return new ResponseEntity<UserTurn>(userTurnService.createUserTurn(id_user, id_turn), HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/turns/{id_turn}/users")
    public ResponseEntity<List<UserResponse>> getUsersByTurn(@PathVariable("id_turn") Long id_turn){
        return new ResponseEntity<List<UserResponse>>(userTurnService.getUsersByTurn(id_turn), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/users/{id_user}/turns")
    public ResponseEntity<List<TurnResponse>> getTurnsByUser(@PathVariable("id_user") Long id_user){
        return new ResponseEntity<List<TurnResponse>>(userTurnService.getTurnsByUser(id_user), HttpStatus.OK);
    }
}

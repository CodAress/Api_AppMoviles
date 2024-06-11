package upc.edu.LoggyAPI.turn.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Gestión de Turnos y Usuarios", description = "Controlador para operaciones relacionadas con turnos y usuarios")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class UserTurnController {

    @Autowired
    private UserTurnService userTurnService;

    @Operation(summary = "Añade un usuario a un turno por ID", description = "Este endpoint añade un usuario a un turno y devuelve los detalles del usuario en el turno.")
    @Transactional
    @PostMapping(value = "/users/{id_user}/turns/{id_turn}")
    public ResponseEntity<UserTurn> addUserToTurn(@PathVariable("id_user") Long id_user, @PathVariable("id_turn") Long id_turn){
        return new ResponseEntity<UserTurn>(userTurnService.createUserTurn(id_user, id_turn), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene los usuarios de un turno por ID", description = "Este endpoint devuelve los detalles de los usuarios en un turno por su id.")
    @Transactional
    @GetMapping(value = "/turns/{id_turn}/users")
    public ResponseEntity<List<UserResponse>> getUsersByTurn(@PathVariable("id_turn") Long id_turn){
        return new ResponseEntity<List<UserResponse>>(userTurnService.getUsersByTurn(id_turn), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene los turnos de un usuario por ID", description = "Este endpoint devuelve los detalles de los turnos de un usuario por su id.")
    @Transactional
    @GetMapping(value = "/users/{id_user}/turns")
    public ResponseEntity<List<TurnResponse>> getTurnsByUser(@PathVariable("id_user") Long id_user){
        return new ResponseEntity<List<TurnResponse>>(userTurnService.getTurnsByUser(id_user), HttpStatus.OK);
    }
}

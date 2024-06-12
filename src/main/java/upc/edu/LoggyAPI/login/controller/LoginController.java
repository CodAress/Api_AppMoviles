package upc.edu.LoggyAPI.login.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.login.model.LoginRequest;
import upc.edu.LoggyAPI.login.service.LoginService;
@Tag(name = "Gestión de Login", description = "Controlador para operaciones relacionadas con login")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class LoginController {
    @Autowired
    private LoginService loggingService;
    @Operation(summary = "Inicia sesión", description = "Este endpoint inicia sesión y devuelve un booleano indicando si se inició sesión o no.")
    @Transactional
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest loginRequest) {
        Boolean login = loggingService.login(loginRequest);
        return new ResponseEntity<Boolean>(login, HttpStatus.OK);
    }

}

package upc.edu.LoggyAPI.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.login.model.LoginRequest;
import upc.edu.LoggyAPI.login.service.LoginService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class LoginController {
    @Autowired
    private LoginService loggingService;

    @Transactional
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest loginRequest) {
        Boolean login = loggingService.login(loginRequest);
        return new ResponseEntity<Boolean>(login, HttpStatus.OK);
    }

}

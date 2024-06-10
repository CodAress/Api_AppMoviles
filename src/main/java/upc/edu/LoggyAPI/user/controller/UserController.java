package upc.edu.LoggyAPI.user.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.user.dto.ProfileRequest;
import upc.edu.LoggyAPI.user.dto.UserRequest;
import upc.edu.LoggyAPI.user.dto.UserResponse;
import upc.edu.LoggyAPI.user.dto.mapper.ProfileMapper;
import upc.edu.LoggyAPI.user.dto.mapper.UserMapper;
import upc.edu.LoggyAPI.user.service.UserService;

import java.util.List;

@RestController(value = "Users")
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class UserController {
    @Autowired
    private UserService userService;
    /*
    * @ApiOperation(value = "Crea un nuevo usuario")
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "Usuario creado con éxito"),
    @ApiResponse(code = 401, message = "No estás autorizado para ver el recurso"),
    @ApiResponse(code = 403, message = "El acceso al recurso que estás intentando acceder está prohibido"),
    @ApiResponse(code = 404, message = "El recurso que estás intentando acceder no se encuentra")
})
    * */
    //@ApiOperation(value = "Crea un nuevo usuario", notes = "Este endpoint crea un nuevo usuario y devuelve los detalles del usuario creado.")
    /*
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado con éxito"),
            @ApiResponse(responseCode = "401", description = "No estás autorizado para ver el recurso"),
            @ApiResponse(responseCode = "403", description = "El acceso al recurso que estás intentando acceder está prohibido"),
            @ApiResponse(responseCode = "404", description = "El recurso que estás intentando acceder no se encuentra")
    })
    */

    @Transactional
    @PostMapping(value = "/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        var user = UserMapper.INSTANCE.userRequestToUser(userRequest);
        System.out.println(user);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.createUser(user));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping(value = "/users/{id}/profile")
    public ResponseEntity<UserResponse> addProfile(@PathVariable("id") Long id, @RequestBody ProfileRequest profileRequest){
        var profile = ProfileMapper.INSTANCE.profileRequestToProfile(profileRequest);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.addProfileToUser(id, profile));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/users/{id}/profile")
    public ResponseEntity<UserResponse> editProfile(@PathVariable("id") Long id, @RequestBody ProfileRequest profileRequest){
        var profile = ProfileMapper.INSTANCE.profileRequestToProfile(profileRequest);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.editProfileToUser(id, profile));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        var usersResponse = UserMapper.INSTANCE.usersToUsersResponse(userService.getAllUsers());
        return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.getUserById(id));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest){
        var user = UserMapper.INSTANCE.userRequestToUser(userRequest);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.updateUser(id, user));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        var valor = userService.deleteUser(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }

}

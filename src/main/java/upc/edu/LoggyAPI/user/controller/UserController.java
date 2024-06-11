package upc.edu.LoggyAPI.user.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import upc.edu.LoggyAPI.utils.exception.ErrorMessage;

import java.util.List;

@RestController(value = "Users")
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
@Tag(name = "Gestión de Usuarios", description = "Controlador para operaciones relacionadas con usuarios")
public class UserController {
    @Autowired
    private UserService userService;
    @Operation(summary = "Crea un nuevo usuario", description = "Este endpoint crea un nuevo usuario y devuelve los detalles del usuario creado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado con éxito",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Petición incorrecta",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "401", description = "No estás autorizado para ver el recurso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "403", description = "El acceso al recurso que estás intentando acceder está prohibido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "404", description = "El recurso que estás intentando acceder no se encuentra"
                    , content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorMessage.class)))
    })
    @Transactional
    @PostMapping(value = "/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        var user = UserMapper.INSTANCE.userRequestToUser(userRequest);
        System.out.println(user);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.createUser(user));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Añade un perfil a un usuario por su ID", description = "Este endpoint añade un perfil a un usuario y devuelve los detalles del usuario con el perfil añadido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfil añadido con éxito"),
            @ApiResponse(responseCode = "401", description = "No estás autorizado para ver el recurso"),
            @ApiResponse(responseCode = "403", description = "El acceso al recurso que estás intentando acceder está prohibido"),
            @ApiResponse(responseCode = "404", description = "El recurso que estás intentando acceder no se encuentra")
    })
    @Transactional
    @PostMapping(value = "/users/{id}/profile")
    public ResponseEntity<UserResponse> addProfile(@PathVariable("id") Long id, @RequestBody ProfileRequest profileRequest){
        var profile = ProfileMapper.INSTANCE.profileRequestToProfile(profileRequest);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.addProfileToUser(id, profile));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Edita el perfil de un usuario por su ID", description = "Este endpoint edita el perfil de un usuario y devuelve los detalles del usuario con el perfil editado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil editado con éxito"),
            @ApiResponse(responseCode = "401", description = "No estás autorizado para ver el recurso"),
            @ApiResponse(responseCode = "403", description = "El acceso al recurso que estás intentando acceder está prohibido"),
            @ApiResponse(responseCode = "404", description = "El recurso que estás intentando acceder no se encuentra")
    })
    @Transactional
    @PutMapping(value = "/users/{id}/profile")
    public ResponseEntity<UserResponse> editProfile(@PathVariable("id") Long id, @RequestBody ProfileRequest profileRequest){
        var profile = ProfileMapper.INSTANCE.profileRequestToProfile(profileRequest);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.editProfileToUser(id, profile));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene todos los usuarios", description = "Este endpoint devuelve todos los usuarios registrados en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos con éxito"),
            @ApiResponse(responseCode = "401", description = "No estás autorizado para ver el recurso"),
            @ApiResponse(responseCode = "403", description = "El acceso al recurso que estás intentando acceder está prohibido"),
            @ApiResponse(responseCode = "404", description = "El recurso que estás intentando acceder no se encuentra")
    })
    @Transactional
    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        var usersResponse = UserMapper.INSTANCE.usersToUsersResponse(userService.getAllUsers());
        return new ResponseEntity<List<UserResponse>>(usersResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario por su ID", description = "Este endpoint devuelve un usuario por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido con éxito"),

            @ApiResponse(responseCode = "404", description = "El recurso que estás intentando acceder no se encuentra"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @Transactional
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.getUserById(id));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un usuario por su ID", description = "Este endpoint actualiza un usuario y devuelve los detalles del usuario actualizado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado con éxito"),
            @ApiResponse(responseCode = "401", description = "No estás autorizado para ver el recurso"),
            @ApiResponse(responseCode = "403", description = "El acceso al recurso que estás intentando acceder está prohibido"),
            @ApiResponse(responseCode = "404", description = "El recurso que estás intentando acceder no se encuentra")
    })
    @Transactional
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest){
        var user = UserMapper.INSTANCE.userRequestToUser(userRequest);
        var userResponse = UserMapper.INSTANCE.userToUserResponse(userService.updateUser(id, user));
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un usuario por su ID", description = "Este endpoint elimina un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado con éxito"),
            @ApiResponse(responseCode = "401", description = "No estás autorizado para ver el recurso"),
            @ApiResponse(responseCode = "403", description = "El acceso al recurso que estás intentando acceder está prohibido"),
            @ApiResponse(responseCode = "404", description = "El recurso que estás intentando acceder no se encuentra")
    })
    @Transactional
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        var valor = userService.deleteUser(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }

}

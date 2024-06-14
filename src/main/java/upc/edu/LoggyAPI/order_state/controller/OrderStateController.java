package upc.edu.LoggyAPI.order_state.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.order_state.dto.OrderStateResponse;
import upc.edu.LoggyAPI.order_state.dto.mapper.OrderStateMapper;
import upc.edu.LoggyAPI.order_state.service.OrderStateService;

import java.util.List;

@Tag(name = "Gestión de Estados de Orden", description = "Controlador para operaciones relacionadas con estados de orden")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class OrderStateController {
    @Autowired
    private OrderStateService orderStateService;

    @Operation(summary = "Crea un nuevo estado de orden indicando fallos", description = "Este endpoint crea un nuevo estado de orden con fallos y devuelve los detalles del nuevo estado de orden creado.")
    @PostMapping("/order_state/order/{order_id}/product/{product_id}/batch/{batch_id}/fail/{fail_id}")
    public ResponseEntity<OrderStateResponse> createOrderState(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id, @PathVariable("batch_id") Long batch_id, @PathVariable("fail_id") Long fail_id){
        OrderStateResponse orderStateResponse = orderStateService.createOrderState(order_id, product_id, batch_id, fail_id);
        return new ResponseEntity<>(orderStateResponse, HttpStatus.CREATED);
    }
    @Operation(summary = "Elimina un estado de orden por su ID", description = "Este endpoint elimina un estado de orden y devuelve un booleano indicando si se eliminó o no.")
    @DeleteMapping("/order_state/order/{order_id}/product/{product_id}")
    public ResponseEntity<Boolean> deleteOrderState(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id){
        return new ResponseEntity<Boolean>(orderStateService.deleteOrderState(order_id, product_id), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un estado de orden por su ID", description = "Este endpoint devuelve un estado de orden por su ID.")
    @GetMapping("/order_state/order/{order_id}/product/{product_id}")
    public ResponseEntity<OrderStateResponse> getOrderStateById(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id){
        return new ResponseEntity<>(orderStateService.getOrderStateById(order_id, product_id), HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un estado de orden por su ID", description = "Este endpoint actualiza un estado de orden por su ID y devuelve los detalles del estado de orden actualizado.")
    @PutMapping("/order_state/order/{order_id}/product/{product_id}/batch/{batch_id}/fail/{fail_id}")
    public ResponseEntity<OrderStateResponse> updateOrderState(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id, @PathVariable("batch_id") Long batch_id, @PathVariable("fail_id") Long fail_id){
        return new ResponseEntity<>(orderStateService.updateOrderState(order_id,product_id,batch_id,fail_id), HttpStatus.OK);
    }

    @Operation(summary = "Crea un nuevo estado de orden sin fallos", description = "Este endpoint crea un nuevo estado de orden sin fallos y devuelve los detalles del nuevo estado de orden creado.")
    @PostMapping("/order_state/order/{order_id}/product/{product_id}/batch/{batch_id}")
    public ResponseEntity<OrderStateResponse> createOrderState(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id, @PathVariable("batch_id") Long batch_id){
        return new ResponseEntity<>(orderStateService.createOrderState(order_id,product_id,batch_id), HttpStatus.CREATED);
    }


}

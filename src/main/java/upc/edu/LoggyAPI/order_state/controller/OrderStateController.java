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
        var orderStateResponse = OrderStateMapper.INSTANCE.orderStateToOrderStateResponse(orderStateService.createOrderState(order_id, product_id, batch_id, fail_id));
        return new ResponseEntity<OrderStateResponse>(orderStateResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Crea un nuevo estado de orden sin ningun fallo", description = "Este endpoint crea un nuevo estado de orden sin fallos y devuelve los detalles del nuevo estado de orden creado.")
    @PostMapping("/order_state/order/{order_id}/product/{product_id}/batch/{batch_id}")
    public ResponseEntity<OrderStateResponse> createOrderState(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id, @PathVariable("batch_id") Long batch_id){
        var orderStateResponse = OrderStateMapper.INSTANCE.orderStateToOrderStateResponse(orderStateService.createOrderState(order_id, product_id, batch_id));
        return new ResponseEntity<OrderStateResponse>(orderStateResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un estado de orden por su ID", description = "Este endpoint devuelve los detalles de un estado de orden por su id.")
    @GetMapping("/order_state/order/{order_id}/product/{product_id}")
    public ResponseEntity<OrderStateResponse> getOrderStateById(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id){
        var orderStateResponse = OrderStateMapper.INSTANCE.orderStateToOrderStateResponse(orderStateService.getOrderStateById(order_id, product_id));
        return new ResponseEntity<OrderStateResponse>(orderStateResponse, HttpStatus.OK);
    }

    /*
    @Operation(summary = "Actualiza un estado de orden por su ID", description = "Este endpoint actualiza un estado de orden y devuelve los detalles del estado de orden actualizado.")
    @PutMapping("/order_state/order/{order_id}/product/{product_id}")
    public ResponseEntity<OrderStateResponse> updateOrderState(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id, @RequestBody OrderStateResponse orderStateResponse){
        var orderState = OrderStateMapper.INSTANCE.orderStateResponseToOrderState(orderStateResponse);
        var orderStateResponseUpdated = OrderStateMapper.INSTANCE.orderStateToOrderStateResponse(orderStateService.updateOrderState(order_id, product_id, orderState));
        return new ResponseEntity<OrderStateResponse>(orderStateResponseUpdated, HttpStatus.OK);
    }
    */

    @Operation(summary = "Elimina un estado de orden por su ID", description = "Este endpoint elimina un estado de orden y devuelve un booleano indicando si se eliminó o no.")
    @DeleteMapping("/order_state/order/{order_id}/product/{product_id}")
    public ResponseEntity<Boolean> deleteOrderState(@PathVariable("order_id") Long order_id, @PathVariable("product_id") Long product_id){
        return new ResponseEntity<Boolean>(orderStateService.deleteOrderState(order_id, product_id), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los estados de orden", description = "Este endpoint devuelve los detalles de todos los estados de orden.")
    @GetMapping("/order_state")
    public ResponseEntity<List<OrderStateResponse>> getAllOrderStates(){
        var orderStatesResponse = OrderStateMapper.INSTANCE.orderStatesToOrderStateResponses(orderStateService.getAllOrderStates());
        return new ResponseEntity<List<OrderStateResponse>>(orderStatesResponse, HttpStatus.OK);
    }
}

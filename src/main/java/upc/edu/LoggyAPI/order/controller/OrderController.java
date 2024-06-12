package upc.edu.LoggyAPI.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.order.dto.OrderRequest;
import upc.edu.LoggyAPI.order.dto.OrderResponse;
import upc.edu.LoggyAPI.order.dto.mapper.OrderMapper;
import upc.edu.LoggyAPI.order.service.OrderService;

import java.util.List;

@Tag(name = "Gestión de Ordenes", description = "Controlador para operaciones relacionadas con ordenes")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Operation(summary = "Crea una nueva orden perteneciente a una linea de producción", description = "Este endpoint crea una nueva orden a una línea de producción y devuelve los detalles de la nueva orden creada.")
    @Transactional
    @PostMapping(value = "/lines/{id_line}/orders")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable("id_line") Long id_line, @RequestBody OrderRequest orderRequest){
        var order = OrderMapper.INSTANCE.orderRequestToOrder(orderRequest);
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(orderService.createOrder(id_line, order));
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene una orden por su ID", description = "Este endpoint devuelve los detalles de una orden por su id.")
    @Transactional
    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long id){
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(orderService.getOrderById(id));
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }
    @Operation(summary = "Obtiene todas las ordenes", description = "Este endpoint devuelve los detalles de todas las ordenes.")
    @Transactional
    @GetMapping(value = "/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        var ordersResponse = OrderMapper.INSTANCE.ordersToOrderResponses(orderService.getAllOrders());
        return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza una orden por su ID", description = "Este endpoint actualiza una orden y devuelve los detalles de la orden actualizada.")
    @Transactional
    @PutMapping(value = "/orders/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable("id") Long id, @RequestBody OrderRequest orderRequest){
        var order = OrderMapper.INSTANCE.orderRequestToOrder(orderRequest);
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(orderService.updateOrder(id, order));
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }
    @Operation(summary = "Elimina una orden por su ID", description = "Este endpoint elimina una orden y devuelve un booleano indicando si se eliminó o no.")
    @Transactional
    @DeleteMapping(value = "/orders/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable("id") Long id){
        var valor = orderService.deleteOrder(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

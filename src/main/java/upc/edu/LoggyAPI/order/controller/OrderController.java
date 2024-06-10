package upc.edu.LoggyAPI.order.controller;

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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Transactional
    @PostMapping(value = "/lines/{id_line}/orders")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable("id_line") Long id_line, @RequestBody OrderRequest orderRequest){
        var order = OrderMapper.INSTANCE.orderRequestToOrder(orderRequest);
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(orderService.createOrder(id_line, order));
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/orders/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") Long id){
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(orderService.getOrderById(id));
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/orders")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        var ordersResponse = OrderMapper.INSTANCE.ordersToOrderResponses(orderService.getAllOrders());
        return new ResponseEntity<List<OrderResponse>>(ordersResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/orders/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable("id") Long id, @RequestBody OrderRequest orderRequest){
        var order = OrderMapper.INSTANCE.orderRequestToOrder(orderRequest);
        var orderResponse = OrderMapper.INSTANCE.orderToOrderResponse(orderService.updateOrder(id, order));
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/orders/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable("id") Long id){
        var valor = orderService.deleteOrder(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

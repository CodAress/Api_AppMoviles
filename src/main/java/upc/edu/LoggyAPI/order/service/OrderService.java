package upc.edu.LoggyAPI.order.service;

import upc.edu.LoggyAPI.order.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Long id_line, Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    Order updateOrder(Long id, Order order);
    boolean deleteOrder(Long id);
}

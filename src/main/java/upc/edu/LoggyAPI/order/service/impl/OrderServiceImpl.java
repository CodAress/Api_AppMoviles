package upc.edu.LoggyAPI.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.line.repository.LineRepository;
import upc.edu.LoggyAPI.order.model.Order;
import upc.edu.LoggyAPI.order.repository.OrderRepository;
import upc.edu.LoggyAPI.order.service.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private LineRepository lineRepository;

    @Override
    public Order createOrder(Long id_line, Order order) {
        validateOrder(order);
        existLineById(id_line);
        order.setLine(lineRepository.findById(id_line).get());
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        existOrderById(id);
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new IllegalArgumentException("Unregistered orders");
        }
        return orders;
    }

    @Override
    public Order updateOrder(Long id, Order order) {
        existOrderById(id);
        validateOrder(order);
        Order orderToUpdate = orderRepository.findById(id).get();
        orderToUpdate.setFirms(order.getFirms());
        orderToUpdate.setQuantity(order.getQuantity());
        orderToUpdate.setState(order.getState());
        return orderRepository.save(orderToUpdate);
    }

    @Override
    public boolean deleteOrder(Long id) {
        existOrderById(id);
        orderRepository.deleteById(id);
        return true;
    }
    private void validateOrder(Order order) {
        if (order.getFirms() == null) {
            throw new IllegalArgumentException("Firms cannot be empty");
        }
        if(order.getFirms() < 0) {
            throw new IllegalArgumentException("Firms cannot be negative");
        }
        if (order.getQuantity() == null) {
            throw new IllegalArgumentException("Quantity cannot be empty");
        }
        if(order.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (order.getState() == null) {
            throw new IllegalArgumentException("State cannot be empty");
        }
        if(order.getState() < 0) {
            throw new IllegalArgumentException("State cannot be negative");
        }
    }
    private void existOrderById(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("Order with id %s not found", id));
        }
    }
    private void existLineById(Long id) {
        if (!lineRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("Line with id %s not found", id));
        }
    }
}

package upc.edu.LoggyAPI.order_state.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.order.model.Order;
import upc.edu.LoggyAPI.order.repository.OrderRepository;
import upc.edu.LoggyAPI.order_state.model.Batch;
import upc.edu.LoggyAPI.order_state.model.Fail;
import upc.edu.LoggyAPI.order_state.model.OrderState;
import upc.edu.LoggyAPI.order_state.model.OrderStateId;
import upc.edu.LoggyAPI.order_state.repository.BatchRepository;
import upc.edu.LoggyAPI.order_state.repository.FailRepository;
import upc.edu.LoggyAPI.order_state.repository.OrderStateRepository;
import upc.edu.LoggyAPI.order_state.service.OrderStateService;
import upc.edu.LoggyAPI.product.model.Product;
import upc.edu.LoggyAPI.product.repository.ProductRepository;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class OrderStateServiceImpl implements OrderStateService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FailRepository failRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderStateRepository orderStateRepository;
    @Transactional
    @Override
    public OrderState createOrderState(Long order_id, Long product_id, Long batch_id, Long fail_id) {
        existOrderById(order_id);
        existProductById(product_id);
        existBatchById(batch_id);
        existFailById(fail_id);
        Order order = orderRepository.findById(order_id).get();
        Product product = productRepository.findById(product_id).get();
        Batch batch = batchRepository.findById(batch_id).get();
        Fail fail = failRepository.findById(fail_id).get();
        OrderState orderState = OrderState.builder()
                .order(order)
                .product(product)
                .batch(batch)
                .fail(fail)
                .build();
        return orderStateRepository.save(orderState);
    }

    @Override
    public OrderState createOrderState(Long order_id, Long product_id, Long batch_id) {
        existBatchById(batch_id);
        existOrderById(order_id);
        existProductById(product_id);
        Order order = orderRepository.findById(order_id).get();
        Product product = productRepository.findById(product_id).get();
        Batch batch = batchRepository.findById(batch_id).get();
        OrderState orderState = OrderState.builder()
                .order(order)
                .product(product)
                .batch(batch)
                .fail(null)
                .build();
        return orderStateRepository.save(orderState);
    }

    @Transactional
    @Override
    public OrderState getOrderStateById(Long order_id, Long product_id) {
        existOrderById(order_id);
        existProductById(product_id);
        OrderStateId orderStateId = OrderStateId.builder()
                .order(order_id)
                .product(product_id)
                .build();
        return orderStateRepository.findById(orderStateId).get();
    }

    @Transactional
    @Override
    public OrderState updateOrderState(Long order_id, Long product_id, OrderState orderState) {
        //Mejorar el codigo
        existOrderById(order_id);
        existProductById(product_id);
        OrderStateId orderStateId = OrderStateId.builder()
                .order(order_id)
                .product(product_id)
                .build();
        OrderState orderStateToUpdate = orderStateRepository.findById(orderStateId).get();
        orderStateToUpdate.setBatch(orderState.getBatch());
        orderStateToUpdate.setFail(orderState.getFail());
        return orderStateRepository.save(orderStateToUpdate);
    }
    @Transactional
    @Override
    public Boolean deleteOrderState(Long order_id, Long product_id) {
        existOrderById(order_id);
        existProductById(product_id);
        OrderStateId orderStateId = OrderStateId.builder()
                .order(order_id)
                .product(product_id)
                .build();
        orderStateRepository.deleteById(orderStateId);
        return true;
    }
    @Transactional
    @Override
    public List<OrderState> getAllOrderStates() {
        List<OrderState> orderStates = orderStateRepository.findAll();
        if(orderStates.isEmpty()){
            throw new ResourceNotFoundException("Order states not found");
        }
        return orderStates;
    }

    private void existOrderById(Long order_id) {
        if (!orderRepository.existsById(order_id)) {
            throw new ResourceNotFoundException(String.format("Order with id %s not found", order_id));
        }
    }
    private void existProductById(Long product_id) {
        if (!productRepository.existsById(product_id)) {
            throw new ResourceNotFoundException(String.format("Product with id %s not found", product_id));
        }
    }
    private void existBatchById(Long batch_id) {
        if (!batchRepository.existsById(batch_id)) {
            throw new ResourceNotFoundException(String.format("Batch with id %s not found", batch_id));
        }
    }
    private void existFailById(Long fail_id) {
        if (!failRepository.existsById(fail_id)) {
            throw new ResourceNotFoundException(String.format("Fail with id %s not found", fail_id));
        }
    }
}

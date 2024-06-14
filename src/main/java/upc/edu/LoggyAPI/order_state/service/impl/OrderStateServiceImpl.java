package upc.edu.LoggyAPI.order_state.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.order.model.Order;
import upc.edu.LoggyAPI.order.repository.OrderRepository;
import upc.edu.LoggyAPI.order_state.dto.OrderStateResponse;
import upc.edu.LoggyAPI.order_state.dto.mapper.OrderStateMapper;
import upc.edu.LoggyAPI.order_state.model.Batch;
import upc.edu.LoggyAPI.order_state.model.Fail;
import upc.edu.LoggyAPI.order_state.model.OrderState;

import upc.edu.LoggyAPI.order_state.model.OrderStatePK;
import upc.edu.LoggyAPI.order_state.repository.BatchRepository;
import upc.edu.LoggyAPI.order_state.repository.FailRepository;
import upc.edu.LoggyAPI.order_state.repository.OrderStateRepository;
import upc.edu.LoggyAPI.order_state.service.OrderStateService;
import upc.edu.LoggyAPI.product.dto.mapper.ProductMapper;
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
    public OrderStateResponse createOrderState(Long order_id, Long product_id, Long batch_id, Long fail_id) {
        existOrderById(order_id);
        existProductById(product_id);
        existBatchById(batch_id);
        existFailById(fail_id);

        OrderStatePK orderStatePK = OrderStatePK.builder()
                .orderId(order_id)
                .productId(product_id)
                .build();

        existOrderStateById(orderStatePK);

        Order order = orderRepository.findById(order_id).get();
        Product product = productRepository.findById(product_id).get();
        Batch batch = batchRepository.findById(batch_id).get();
        Fail fail = failRepository.findById(fail_id).get();



        OrderState orderState = OrderState.builder()
                .id(orderStatePK)
                .order(order)
                .batch(batch)
                .fail(fail)
                .build();

        orderStateRepository.save(orderState);

        order.getOrderStates().add(orderState);
        orderRepository.save(order);

        OrderStateResponse response = OrderStateMapper.INSTANCE.orderStateToOrderStateResponse(orderState);
        response.setProduct(ProductMapper.INSTANCE.productToProductResponse(product));
        return response;
    }


    @Override
    public OrderStateResponse createOrderState(Long order_id, Long product_id, Long batch_id) {
        existBatchById(batch_id);
        existOrderById(order_id);
        existProductById(product_id);

        OrderStatePK orderStatePK = OrderStatePK.builder()
                .orderId(order_id)
                .productId(product_id)
                .build();

        existOrderStateById(orderStatePK);

        Order order = orderRepository.findById(order_id).get();
        Product product = productRepository.findById(product_id).get();
        Batch batch = batchRepository.findById(batch_id).get();


        OrderState orderState = OrderState.builder()
                .id(orderStatePK)
                .order(order)
                .batch(batch)
                .fail(null)
                .build();

        orderStateRepository.save(orderState);

        order.getOrderStates().add(orderState);
        orderRepository.save(order);

        OrderStateResponse response = OrderStateMapper.INSTANCE.orderStateToOrderStateResponse(orderState);
        response.setProduct(ProductMapper.INSTANCE.productToProductResponse(product));
        return response;
    }

    @Transactional
    @Override
    public OrderStateResponse getOrderStateById(Long order_id, Long product_id) {
        existOrderById(order_id);
        existProductById(product_id);
        OrderStatePK orderStatePK = OrderStatePK.builder()
                .orderId(order_id)
                .productId(product_id)
                .build();

        dontExistOrderStateById(orderStatePK);

        OrderState orderState = orderStateRepository.findById(orderStatePK).get();
        Product product = productRepository.findById(product_id).get();
        OrderStateResponse response = OrderStateMapper.INSTANCE.orderStateToOrderStateResponse(orderState);
        response.setProduct(ProductMapper.INSTANCE.productToProductResponse(product));
        return response;
    }

    @Transactional
    @Override
    public OrderStateResponse updateOrderState(Long order_id, Long product_id, Long batch_id, Long fail_id) {
        //Mejorar el codigo
        existOrderById(order_id);
        existProductById(product_id);

        OrderStatePK orderStatePK = OrderStatePK.builder()
                .orderId(order_id)
                .productId(product_id)
                .build();

        dontExistOrderStateById(orderStatePK);

        OrderState orderStateToUpdate = orderStateRepository.findById(orderStatePK).get();
        orderStateToUpdate.setBatch(batchRepository.findById(batch_id).get());
        orderStateToUpdate.setFail(failRepository.findById(fail_id).get());
        orderStateRepository.save(orderStateToUpdate);
        OrderStateResponse response = OrderStateMapper.INSTANCE.orderStateToOrderStateResponse(orderStateToUpdate);
        response.setProduct(ProductMapper.INSTANCE.productToProductResponse(productRepository.findById(product_id).get()));
        return response;
    }
    @Transactional
    @Override
    public Boolean deleteOrderState(Long order_id, Long product_id) {
        existOrderById(order_id);
        existProductById(product_id);
        OrderStatePK orderStatePK = OrderStatePK.builder()
                .orderId(order_id)
                .productId(product_id)
                .build();
        dontExistOrderStateById(orderStatePK);
        orderStateRepository.deleteById(orderStatePK);
        return true;
    }
    @Transactional
    @Override
    public List<OrderStateResponse> getAllOrderStates() {
        List<OrderState> orderStates = orderStateRepository.findAll();
        if(orderStates.isEmpty()){
            throw new ResourceNotFoundException("Order states not found");
        }
        return OrderStateMapper.INSTANCE.orderStatesToOrderStateResponses(orderStates);
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
    private void existOrderStateById(OrderStatePK orderStatePK) {
        if (orderStateRepository.existsById(orderStatePK)) {
            throw new ResourceNotFoundException(String.format("Order state with id %s already exist", orderStatePK));
        }
    }
    private void dontExistOrderStateById(OrderStatePK orderStatePK) {
        if (!orderStateRepository.existsById(orderStatePK)) {
            throw new ResourceNotFoundException(String.format("Order state with id %s not found", orderStatePK));
        }
    }
}

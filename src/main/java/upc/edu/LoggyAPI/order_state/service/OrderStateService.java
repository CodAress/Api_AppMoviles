package upc.edu.LoggyAPI.order_state.service;

import upc.edu.LoggyAPI.order.model.Order;
import upc.edu.LoggyAPI.order_state.model.OrderState;

import java.util.List;

public interface OrderStateService {
    OrderState createOrderState(Long order_id, Long product_id, Long batch_id, Long fail_id);
    OrderState createOrderState(Long order_id, Long product_id, Long batch_id);
    OrderState getOrderStateById(Long order_id, Long product_id);
    OrderState updateOrderState(Long order_id, Long product_id, OrderState orderState);
    Boolean deleteOrderState(Long order_id, Long product_id);
    List<OrderState> getAllOrderStates();
}

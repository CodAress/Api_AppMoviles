package upc.edu.LoggyAPI.order_state.service;

import upc.edu.LoggyAPI.order.model.Order;
import upc.edu.LoggyAPI.order_state.dto.OrderStateResponse;
import upc.edu.LoggyAPI.order_state.model.OrderState;

import java.util.List;

public interface OrderStateService {
    OrderStateResponse createOrderState(Long order_id, Long product_id, Long batch_id, Long fail_id);
    OrderStateResponse createOrderState(Long order_id, Long product_id, Long batch_id);
    OrderStateResponse getOrderStateById(Long order_id, Long product_id);
    OrderStateResponse updateOrderState(Long order_id, Long product_id, Long batch_id, Long fail_id);

    Boolean deleteOrderState(Long order_id, Long product_id);
    List<OrderStateResponse> getAllOrderStates();
}

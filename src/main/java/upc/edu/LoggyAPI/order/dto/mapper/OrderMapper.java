package upc.edu.LoggyAPI.order.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.order.dto.OrderRequest;
import upc.edu.LoggyAPI.order.dto.OrderResponse;
import upc.edu.LoggyAPI.order.model.Order;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order orderRequestToOrder(OrderRequest orderRequest);
    OrderResponse orderToOrderResponse(Order order);
    List<OrderResponse> ordersToOrderResponses(List<Order> orders);
}

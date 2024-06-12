package upc.edu.LoggyAPI.order_state.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.order_state.dto.OrderStateResponse;
import upc.edu.LoggyAPI.order_state.model.OrderState;

import java.util.List;

@Mapper
public interface OrderStateMapper {
    OrderStateMapper INSTANCE = Mappers.getMapper(OrderStateMapper.class);
    OrderStateResponse orderStateToOrderStateResponse(OrderState orderState);
    List<OrderStateResponse> orderStatesToOrderStateResponses(List<OrderState> orderStates);
}

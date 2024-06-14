package upc.edu.LoggyAPI.order_state.dto;

import lombok.Data;
import upc.edu.LoggyAPI.order.dto.OrderResponse;
import upc.edu.LoggyAPI.order.model.Order;
import upc.edu.LoggyAPI.order_state.model.Batch;
import upc.edu.LoggyAPI.order_state.model.Fail;
import upc.edu.LoggyAPI.product.dto.ProductResponse;
import upc.edu.LoggyAPI.product.model.Product;

@Data
public class OrderStateResponse {
    private OrderResponse order;
    private ProductResponse product;
    private Batch batch;
    private Fail fail;
}

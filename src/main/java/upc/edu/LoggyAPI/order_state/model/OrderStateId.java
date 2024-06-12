package upc.edu.LoggyAPI.order_state.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OrderStateId implements Serializable {
    private Long order;
    private Long product;
}

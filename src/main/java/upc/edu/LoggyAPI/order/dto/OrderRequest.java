package upc.edu.LoggyAPI.order.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Integer firms;
    private Integer quantity;
    private Integer state;
}

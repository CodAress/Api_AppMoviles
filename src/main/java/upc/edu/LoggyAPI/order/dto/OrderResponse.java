package upc.edu.LoggyAPI.order.dto;

import lombok.Data;
import upc.edu.LoggyAPI.line.dto.LineResponse;

@Data
public class OrderResponse {
    private Long id;
    private Integer firms;
    private Integer quantity;
    private Integer state;
    private LineResponse line;
}

package upc.edu.LoggyAPI.order_state.dto;

import lombok.Data;

@Data
public class BatchRequest {
    private Long type;
    private Long count;
}

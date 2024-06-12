package upc.edu.LoggyAPI.order_state.dto;

import lombok.Data;

@Data
public class FailRequest {
    private Long type;
    private Long count;
}

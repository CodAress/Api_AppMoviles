package upc.edu.LoggyAPI.order_state.dto;

import lombok.Data;

@Data
public class FailResponse {
    private Long id;
    private Long type;
    private Long count;
}

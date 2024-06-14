package upc.edu.LoggyAPI.order_state.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BatchResponse {
    private Long id;
    private String name;
    private LocalDate productionDate;
    private LocalDate expireDate;
}

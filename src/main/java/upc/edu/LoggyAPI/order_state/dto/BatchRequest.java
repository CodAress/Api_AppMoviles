package upc.edu.LoggyAPI.order_state.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BatchRequest {
    private String name;
    private LocalDate productionDate;
    private LocalDate expireDate;
}

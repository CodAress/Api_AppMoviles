package upc.edu.LoggyAPI.product.dto;

import lombok.Data;

@Data
public class MeasurementResponse {
    private Long id;
    private String name;
    private Double quantity;
}

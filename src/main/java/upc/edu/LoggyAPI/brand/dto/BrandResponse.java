package upc.edu.LoggyAPI.brand.dto;

import lombok.Data;

@Data
public class BrandResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
}

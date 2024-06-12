package upc.edu.LoggyAPI.product.dto;

import lombok.Data;
import upc.edu.LoggyAPI.brand.dto.BrandResponse;
import upc.edu.LoggyAPI.product.model.Description;

import java.util.List;

@Data
public class ProductResponse {
    private Long id;
    private String codename;
    private String image;
    private BrandResponse brand;
    private List<CategoryResponse> categories;
    private List<DescriptionResponse> descriptions;
    private List<SpecificationResponse> specifications;
    private List<MeasurementResponse> measurements;
}

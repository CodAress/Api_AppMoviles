package upc.edu.LoggyAPI.product.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.product.dto.SpecificationRequest;
import upc.edu.LoggyAPI.product.dto.SpecificationResponse;
import upc.edu.LoggyAPI.product.model.Specification;

import java.util.List;

@Mapper
public interface SpecificationMapper {
    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);
    Specification specificationRequestToSpecification(SpecificationRequest specificationRequest);
    SpecificationResponse specificationToSpecificationResponse(Specification specification);
    List<SpecificationResponse> specificationsToSpecificationResponses(List<Specification> specifications);
}

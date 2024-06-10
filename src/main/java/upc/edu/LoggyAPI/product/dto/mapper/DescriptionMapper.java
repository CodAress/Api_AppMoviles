package upc.edu.LoggyAPI.product.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.product.dto.DescriptionRequest;
import upc.edu.LoggyAPI.product.dto.DescriptionResponse;
import upc.edu.LoggyAPI.product.model.Description;

import java.util.List;

@Mapper
public interface DescriptionMapper {
    DescriptionMapper INSTANCE = Mappers.getMapper(DescriptionMapper.class);

    Description descriptionRequestToDescription(DescriptionRequest descriptionRequest);
    DescriptionResponse descriptionToDescriptionResponse(Description description);
    List<DescriptionResponse> descriptionsToDescriptionResponses(List<Description> descriptions);
}

package upc.edu.LoggyAPI.brand.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.brand.dto.BrandRequest;
import upc.edu.LoggyAPI.brand.dto.BrandResponse;
import upc.edu.LoggyAPI.brand.model.Brand;

import java.util.List;

@Mapper
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand brandRequestToBrand(BrandRequest brandRequest);
    BrandResponse brandToBrandResponse(Brand brand);
    List<BrandResponse> brandsToBrandResponses(List<Brand> brands);
}

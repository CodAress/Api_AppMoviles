package upc.edu.LoggyAPI.product.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.product.dto.ProductRequest;
import upc.edu.LoggyAPI.product.dto.ProductResponse;
import upc.edu.LoggyAPI.product.model.Product;


import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product productRequestToProduct(ProductRequest productRequest);
    ProductResponse productToProductResponse(Product product);
    List<ProductResponse> productsToProductResponses(List<Product> products);
}

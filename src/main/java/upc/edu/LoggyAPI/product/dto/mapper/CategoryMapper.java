package upc.edu.LoggyAPI.product.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.product.dto.CategoryRequest;
import upc.edu.LoggyAPI.product.dto.CategoryResponse;
import upc.edu.LoggyAPI.product.model.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category categoryRequestToCategory(CategoryRequest categoryRequest);
    CategoryResponse categoryToCategoryResponse(Category category);
    List<CategoryResponse> categoriesToCategoryResponses(List<Category> categories);
}

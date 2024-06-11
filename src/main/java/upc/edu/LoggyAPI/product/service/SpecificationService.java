package upc.edu.LoggyAPI.product.service;

import upc.edu.LoggyAPI.product.model.Specification;

import java.util.List;

public interface SpecificationService {
    Specification createSpecification(Specification specification);
    Specification updateSpecification(Long id, Specification specification);
    Boolean deleteSpecification(Long id);
    Specification getSpecificationById(Long id);
    List<Specification> getAllSpecifications();

}

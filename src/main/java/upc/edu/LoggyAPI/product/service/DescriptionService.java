package upc.edu.LoggyAPI.product.service;

import upc.edu.LoggyAPI.product.model.Description;

import java.util.List;

public interface DescriptionService {
    Description createDescription(Description description);
    Description updateDescription(Long description_id, Description description);
    boolean deleteDescription(Long id);
    Description getDescriptionById(Long id);
    List<Description> getAllDescriptions();
}

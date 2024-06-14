package upc.edu.LoggyAPI.product.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.product.model.Specification;
import upc.edu.LoggyAPI.product.repository.SpecificationRepository;
import upc.edu.LoggyAPI.product.service.SpecificationService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecificationRepository specificationRepository;

    @Override
    @Transactional
    public Specification createSpecification(Specification specification) {
        validateSpecification(specification);
        existSpecificationByName(specification);
        return specificationRepository.save(specification);
    }

    @Override
    @Transactional
    public Specification updateSpecification(Long id, Specification specification) {
        existSpecificationById(id);
        validateSpecification(specification);
        Specification specificationToUpdate = specificationRepository.findById(id).get();
        specificationToUpdate.setName(specification.getName());
        specificationToUpdate.setType(specification.getType());
        return specificationRepository.save(specificationToUpdate);
    }

    @Override
    @Transactional
    public Boolean deleteSpecification(Long id) {
        existSpecificationById(id);
        specificationRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Specification getSpecificationById(Long id) {
        existSpecificationById(id);
        return specificationRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Specification> getAllSpecifications() {
        List<Specification> specifications = specificationRepository.findAll();
        if (specifications.isEmpty()) {
            throw new ResourceNotFoundException("Unregistered specifications");
        }
        return specifications;
    }
    private void validateSpecification(Specification specification) {
        if (specification.getName() == null || specification.getName().isEmpty()) {
            throw new IllegalArgumentException("Specification name is required");
        }
        if(specification.getType() == null || specification.getType().isEmpty()){
            throw new IllegalArgumentException("Specification type is required");
        }
    }
    private void existSpecificationByName(Specification specification) {
        if (specificationRepository.existsByNameIgnoreCase(specification.getName())) {
            throw new IllegalArgumentException(String.format("Specification with name %s already exists", specification.getName()));
        }
    }
    private void existSpecificationById(Long id) {
        if (!specificationRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("Specification with id %s not found", id));
        }
    }
}

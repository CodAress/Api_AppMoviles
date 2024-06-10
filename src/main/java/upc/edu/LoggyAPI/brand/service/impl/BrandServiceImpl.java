package upc.edu.LoggyAPI.brand.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.brand.model.Brand;
import upc.edu.LoggyAPI.brand.repository.BrandRepository;
import upc.edu.LoggyAPI.brand.service.BrandService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand createBrand(Brand brand) {
        validateBrand(brand);
        existBrandByName(brand);
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long brand_id, Brand brand) {
        existBrandById(brand_id);
        validateBrand(brand);
        Brand brandToUpdate = brandRepository.findById(brand_id).get();
        brandToUpdate.setName(brand.getName());
        brandToUpdate.setDescription(brand.getDescription());
        brandToUpdate.setImage(brand.getImage());
        return brandRepository.save(brandToUpdate);
    }

    @Override
    public boolean deleteBrand(Long id) {
        existBrandById(id);
        brandRepository.deleteById(id);
        return true;
    }

    @Override
    public Brand getBrandById(Long id) {
        existBrandById(id);
        return brandRepository.findById(id).get();
    }

    @Override
    public List<Brand> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if(brands.isEmpty()){
            throw new ResourceNotFoundException("Unregistered brands");
        }
        return brands;
    }

    private void validateBrand(Brand brand) {
        if (brand.getName() == null || brand.getName().isEmpty()) {
            throw new IllegalArgumentException("Brand name is required");
        }
        if (brand.getDescription() == null || brand.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Brand description is required");
        }
        if (brand.getImage() == null || brand.getImage().isEmpty()) {
            throw new IllegalArgumentException("Brand image is required");
        }
    }

    private void existBrandByName(Brand brand) {
        if (brandRepository.existsByNameIgnoreCase(brand.getName())) {
            throw new IllegalArgumentException(String.format("Brand with name %s already exists", brand.getName()));
        }
    }

    private void existBrandById(Long brand_id) {
        if (!brandRepository.existsById(brand_id)) {
            throw new ResourceNotFoundException(String.format("Brand with id %s not found", brand_id));
        }
    }
}

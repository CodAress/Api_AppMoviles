package upc.edu.LoggyAPI.brand.service;

import upc.edu.LoggyAPI.brand.model.Brand;

import java.util.List;

public interface BrandService {
    Brand createBrand(Brand brand);
    Brand updateBrand(Long brand_id, Brand brand);
    boolean deleteBrand(Long id);
    Brand getBrandById(Long id);
    List<Brand> getAllBrands();
}

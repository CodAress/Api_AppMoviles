package upc.edu.LoggyAPI.brand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.brand.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long>{
    boolean existsByNameIgnoreCase(String name);
}

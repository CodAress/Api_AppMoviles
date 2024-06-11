package upc.edu.LoggyAPI.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.brand.model.Brand;
import upc.edu.LoggyAPI.product.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCodenameIgnoreCase(String codename);
    List<Product> findByBrand(Brand brand);
}

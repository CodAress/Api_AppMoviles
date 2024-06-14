package upc.edu.LoggyAPI.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.brand.model.Brand;
import upc.edu.LoggyAPI.product.model.Category;
import upc.edu.LoggyAPI.product.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCodenameIgnoreCase(String codename);
    List<Product> findByBrand(Brand brand);
    Set<Product> findByCategoriesContains(Category categories);
}

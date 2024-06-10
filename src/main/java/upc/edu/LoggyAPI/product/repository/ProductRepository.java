package upc.edu.LoggyAPI.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByCodenameIgnoreCase(String codename);
}

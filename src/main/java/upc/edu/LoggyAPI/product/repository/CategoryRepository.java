package upc.edu.LoggyAPI.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.product.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameIgnoreCase(String name);
}

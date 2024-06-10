package upc.edu.LoggyAPI.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.product.model.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
    boolean existsByTitleIgnoreCase(String title);
}

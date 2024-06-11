package upc.edu.LoggyAPI.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.product.model.Specification;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {
    boolean existsByNameIgnoreCase(String name);
}

package upc.edu.LoggyAPI.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.product.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    boolean existsByNameIgnoreCase(String name);
}

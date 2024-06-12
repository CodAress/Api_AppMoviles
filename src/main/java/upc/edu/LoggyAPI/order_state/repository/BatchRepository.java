package upc.edu.LoggyAPI.order_state.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.order_state.model.Batch;

public interface BatchRepository extends JpaRepository<Batch,Long> {
    Boolean existsByNameIgnoreCase(String name);
}

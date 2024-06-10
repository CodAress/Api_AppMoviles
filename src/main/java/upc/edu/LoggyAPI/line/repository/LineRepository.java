package upc.edu.LoggyAPI.line.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.line.model.Line;

public interface LineRepository extends JpaRepository<Line, Long> {
    boolean existsByNameIgnoreCase(String name);
}

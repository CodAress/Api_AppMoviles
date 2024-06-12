package upc.edu.LoggyAPI.order_state.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.order_state.model.Fail;

public interface FailRepository extends JpaRepository<Fail,Long> {
}

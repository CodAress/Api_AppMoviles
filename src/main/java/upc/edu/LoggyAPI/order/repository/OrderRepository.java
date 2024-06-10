package upc.edu.LoggyAPI.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

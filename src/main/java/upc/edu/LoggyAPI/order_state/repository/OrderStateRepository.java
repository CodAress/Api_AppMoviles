package upc.edu.LoggyAPI.order_state.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.order_state.model.OrderState;
import upc.edu.LoggyAPI.order_state.model.OrderStatePK;

public interface OrderStateRepository extends JpaRepository<OrderState, OrderStatePK>{
}

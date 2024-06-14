package upc.edu.LoggyAPI.order_state.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.LoggyAPI.order.model.Order;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_states")
public class OrderState{
    @EmbeddedId
    private OrderStatePK id;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "fail_id")
    private Fail fail;
}

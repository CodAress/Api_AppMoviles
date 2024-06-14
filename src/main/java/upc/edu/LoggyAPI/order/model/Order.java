package upc.edu.LoggyAPI.order.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.LoggyAPI.line.model.Line;
import upc.edu.LoggyAPI.order_state.model.OrderState;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_firms")
    private Integer firms;
    @Column(name = "order_quantity")
    private Integer quantity;
    @Column(name = "order_state")
    private Integer state;

    @ManyToOne
    @JoinColumn(name = "order_line_id")
    private Line line;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    List<OrderState> orderStates;
}

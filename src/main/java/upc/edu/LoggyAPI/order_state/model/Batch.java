package upc.edu.LoggyAPI.order_state.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "batchs")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "batch_expire_date", nullable = false)
    private LocalDate expireDate;
    @Column(name = "batch_production_date", nullable = false)
    private LocalDate productionDate;
    @Column(name = "batch_name", nullable = false)
    private String name;
}

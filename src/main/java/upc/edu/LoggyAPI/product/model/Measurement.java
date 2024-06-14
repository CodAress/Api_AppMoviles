package upc.edu.LoggyAPI.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "measurement_name", nullable = false)
    private String name;
    @Column(name = "measurement_quatity", nullable = false, precision = 2)
    private Double quantity;
    /*
    @ManyToMany(mappedBy = "measurements")
    private Set<Product> products;
    */
}

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
@Table(name = "specifications")
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "specification_type", length = 20, nullable = false)
    private String type;
    @Column(name = "specification_name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "specifications")
    private Set<Product> products;
}

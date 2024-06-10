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
@Table(name = "descriptions")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description_title", nullable = false, unique = true)
    private String title;
    @Column(name = "description_text", nullable = false)
    private String text;

    @ManyToMany(mappedBy = "descriptions")
    private Set<Product> products;
}

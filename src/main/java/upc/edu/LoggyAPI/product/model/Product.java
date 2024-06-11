package upc.edu.LoggyAPI.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.LoggyAPI.brand.model.Brand;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_codename", length = 20, nullable = false, unique = true)
    private String codename;
    @Column(name = "product_image", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_brand_id")
    Brand brand;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_descriptions",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "description_id")
    )
    private Set<Description> descriptions;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "product_measurement",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "measurement_id")
    )
    private Set<Measurement> measurements;
}

package upc.edu.LoggyAPI.brand.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_name", nullable = false, unique = true)
    private String name;
    @Column(name = "brand_description", nullable = false)
    private String description;
    @Column(name = "brand_image", nullable = false)
    private String image;
}

package upc.edu.LoggyAPI.line.model;

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
@Table(name = "lines")
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "line_name", nullable = false, unique = true)
    private String name;
    @Column(name = "line_description", nullable = false)
    private String description;
    @Column(name = "line_image", nullable = false)
    private String image;

}

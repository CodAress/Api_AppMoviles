package upc.edu.LoggyAPI.user.model;

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
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "profile_firstname", nullable = false)
    private String firstname;
    @Column(name = "profile_lastname", nullable = false)
    private String lastname;
    @Column(name = "profile_email", nullable = false, unique = true)
    private String email;
    @Column(name = "profile_genre", nullable = false)
    private String genre;
    @Column(name = "profile_birthdate", nullable = false)
    private LocalDate birthdate;
    @Column(name = "profile_address", nullable = false)
    private String address;

    @OneToOne(mappedBy = "profile")
    private User user;
}

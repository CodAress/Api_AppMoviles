package upc.edu.LoggyAPI.turn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.LoggyAPI.user.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "turns")
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "turn_begin_day", nullable = false)
    private LocalDate beginDay;
    @Column(name = "turn_end_day", nullable = false)
    private LocalDate endDay;
    @Column(name = "turn_begin_hour", nullable = false)
    private LocalTime beginHour;
    @Column(name = "turn_end_hour", nullable = false)
    private LocalTime endHour;

    @ManyToMany(mappedBy = "turns")
    private Set<User> users;
}

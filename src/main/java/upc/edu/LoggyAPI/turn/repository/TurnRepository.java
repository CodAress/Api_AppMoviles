package upc.edu.LoggyAPI.turn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upc.edu.LoggyAPI.turn.model.Turn;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TurnRepository extends JpaRepository<Turn, Long> {
    boolean existsByBeginDayAndEndDayAndBeginHourAndEndHour(LocalDate beginDay, LocalDate endDay, LocalTime beginHour, LocalTime endHour);
}

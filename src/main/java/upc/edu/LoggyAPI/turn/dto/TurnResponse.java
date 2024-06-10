package upc.edu.LoggyAPI.turn.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TurnResponse {
    private Long id;
    private LocalDate beginDay;
    private LocalDate endDay;
    private LocalTime beginHour;
    private LocalTime endHour;
}

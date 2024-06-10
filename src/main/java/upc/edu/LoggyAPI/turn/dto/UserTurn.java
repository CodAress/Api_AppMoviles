package upc.edu.LoggyAPI.turn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.LoggyAPI.turn.dto.TurnResponse;
import upc.edu.LoggyAPI.user.dto.UserResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTurn {
    private UserResponse user;
    private TurnResponse turn;
}

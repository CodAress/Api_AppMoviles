package upc.edu.LoggyAPI.turn.service;

import upc.edu.LoggyAPI.turn.dto.TurnResponse;
import upc.edu.LoggyAPI.turn.dto.UserTurn;
import upc.edu.LoggyAPI.user.dto.UserResponse;

import java.util.List;

public interface UserTurnService {
    UserTurn createUserTurn(Long id_user, Long id_turn);
    List<UserResponse> getUsersByTurn(Long id_turn);
    List<TurnResponse> getTurnsByUser(Long id_user);
}

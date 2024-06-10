package upc.edu.LoggyAPI.turn.service;

import upc.edu.LoggyAPI.turn.model.Turn;

import java.util.List;

public interface TurnService {
    Turn createTurn(Turn turn);
    Turn getTurnById(Long id);
    List<Turn> getAllTurns();
    Turn updateTurn(Long id, Turn turn);
    Boolean deleteTurn(Long id);
}

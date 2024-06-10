package upc.edu.LoggyAPI.turn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.turn.model.Turn;
import upc.edu.LoggyAPI.turn.repository.TurnRepository;
import upc.edu.LoggyAPI.turn.service.TurnService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class TurnServiceImpl implements TurnService {

    @Autowired
    private TurnRepository turnRepository;

    @Override
    public Turn createTurn(Turn turn) {
        validateTurn(turn);
        existTurnByBeginDayAndEndDayAndBeginHourAndEndHour(turn);
        return turnRepository.save(turn);
    }

    @Override
    public Turn getTurnById(Long id) {
        existTurnById(id);
        return turnRepository.findById(id).get();
    }

    @Override
    public List<Turn> getAllTurns() {
        List<Turn> turns = turnRepository.findAll();
        if (turns.isEmpty()) {
            throw new ResourceNotFoundException("There are no turns");
        }
        return turns;
    }

    @Override
    public Turn updateTurn(Long id, Turn turn) {
        existTurnById(id);
        validateTurn(turn);
        existTurnByBeginDayAndEndDayAndBeginHourAndEndHour(turn);
        Turn turnToUpdate = turnRepository.findById(id).get();
        turnToUpdate.setBeginDay(turn.getBeginDay());
        turnToUpdate.setEndDay(turn.getEndDay());
        turnToUpdate.setBeginHour(turn.getBeginHour());
        turnToUpdate.setEndHour(turn.getEndHour());
        return turnRepository.save(turnToUpdate);
    }

    @Override
    public Boolean deleteTurn(Long id) {
        existTurnById(id);
        turnRepository.deleteById(id);
        return true;
    }

    private void validateTurn(Turn turn) {
        if (turn.getBeginDay() == null) {
            throw new IllegalArgumentException("The begin day is required");
        }
        if (turn.getEndDay() == null) {
            throw new IllegalArgumentException("The end day is required");
        }
        if(turn.getEndDay().isBefore(turn.getBeginDay())){
            throw new IllegalArgumentException("The end day must be after the begin day");
        }
        if (turn.getBeginHour() == null) {
            throw new IllegalArgumentException("The begin hour is required");
        }
        if (turn.getEndHour() == null) {
            throw new IllegalArgumentException("The end hour is required");
        }
        if(turn.getEndHour().isBefore(turn.getBeginHour())){
            throw new IllegalArgumentException("The end hour must be after the begin hour");
        }
    }
    private void existTurnById(Long id) {
        if (!turnRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("The turn with id %s does not exist", id));
        }
    }
    private void existTurnByBeginDayAndEndDayAndBeginHourAndEndHour(Turn turn) {
        if (turnRepository.existsByBeginDayAndEndDayAndBeginHourAndEndHour(turn.getBeginDay(), turn.getEndDay(), turn.getBeginHour(), turn.getEndHour())) {
            throw new IllegalArgumentException("The turn already exists");
        }
    }
}

package upc.edu.LoggyAPI.turn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.turn.dto.TurnResponse;
import upc.edu.LoggyAPI.turn.dto.mapper.UserTurnMapper;
import upc.edu.LoggyAPI.turn.model.Turn;
import upc.edu.LoggyAPI.turn.dto.UserTurn;
import upc.edu.LoggyAPI.turn.repository.TurnRepository;
import upc.edu.LoggyAPI.turn.service.UserTurnService;
import upc.edu.LoggyAPI.user.dto.UserResponse;
import upc.edu.LoggyAPI.user.model.User;
import upc.edu.LoggyAPI.user.repository.UserRepository;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

@Service
public class UserTurnServiceImpl implements UserTurnService {

    @Autowired
    private TurnRepository turnRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTurnMapper userTurnMapper;
    @Override
    public UserTurn createUserTurn(Long id_user, Long id_turn) {
        existUserById(id_user);
        existTurnById(id_turn);
        Turn turn = turnRepository.findById(id_turn).get();
        User user = userRepository.findById(id_user).get();
        turn.getUsers().add(user);
        user.getTurns().add(turn);
        userRepository.save(user);
        turnRepository.save(turn);
        return userTurnMapper.toUserTurnDto(user, turn);
    }

    @Override
    public List<UserResponse> getUsersByTurn(Long id_turn) {
        existTurnById(id_turn);
        Set<User> users = turnRepository.findById(id_turn).get().getUsers();
        return userTurnMapper.toUserResponseList(users);
    }

    @Override
    public List<TurnResponse> getTurnsByUser(Long id_user) {
        existUserById(id_user);
        Set<Turn> turns = userRepository.findById(id_user).get().getTurns();
        return userTurnMapper.toTurnResponseList(turns);
    }

    private void existUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("The user with id %s does not exist", id));
        }
    }
    private void existTurnById(Long id) {
        if (!turnRepository.existsById(id)) {
            throw new ResourceNotFoundException(String.format("The turn with id %s does not exist", id));
        }
    }
}

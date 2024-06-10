package upc.edu.LoggyAPI.turn.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import upc.edu.LoggyAPI.turn.dto.TurnResponse;
import upc.edu.LoggyAPI.turn.model.Turn;
import upc.edu.LoggyAPI.turn.dto.UserTurn;
import upc.edu.LoggyAPI.user.dto.UserResponse;
import upc.edu.LoggyAPI.user.model.User;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserTurnMapper {

    @Mapping(source = "user", target = "user")
    @Mapping(source = "turn", target = "turn")
    UserTurn toUserTurnDto(User user, Turn turn);
    List<UserResponse> toUserResponseList(Set<User> users);
    List<TurnResponse> toTurnResponseList(Set<Turn> turns);
}
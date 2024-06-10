package upc.edu.LoggyAPI.turn.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.turn.dto.TurnRequest;
import upc.edu.LoggyAPI.turn.dto.TurnResponse;
import upc.edu.LoggyAPI.turn.model.Turn;

import java.util.List;

@Mapper
public interface TurnMapper {
    TurnMapper INSTANCE = Mappers.getMapper(TurnMapper.class);
    Turn turnRequestToTurn(TurnRequest turnRequest);
    TurnResponse turnToTurnResponse(Turn turn);
    List<TurnResponse> turnsToTurnsResponse(List<Turn> turns);
}

package upc.edu.LoggyAPI.order_state.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.order_state.dto.FailRequest;
import upc.edu.LoggyAPI.order_state.dto.FailResponse;
import upc.edu.LoggyAPI.order_state.model.Fail;

import java.util.List;

@Mapper
public interface FailMapper {
    FailMapper INSTANCE = Mappers.getMapper(FailMapper.class);
    Fail failRequestToFail(FailRequest failRequest);
    FailResponse failToFailResponse(Fail fail);
    List<FailResponse> failsToFailsResponse(List<Fail> fails);
}

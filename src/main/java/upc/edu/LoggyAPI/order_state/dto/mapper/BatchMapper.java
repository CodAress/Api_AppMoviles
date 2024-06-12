package upc.edu.LoggyAPI.order_state.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.order_state.dto.BatchRequest;
import upc.edu.LoggyAPI.order_state.dto.BatchResponse;
import upc.edu.LoggyAPI.order_state.model.Batch;

import java.util.List;

@Mapper
public interface BatchMapper {
    BatchMapper INSTANCE = Mappers.getMapper(BatchMapper.class);
    Batch batchRequestToBatch(BatchRequest batchRequest);
    BatchResponse batchToBatchResponse(Batch batch);
    List<BatchResponse> batchesToBatchesResponse(List<Batch> batchList);
}

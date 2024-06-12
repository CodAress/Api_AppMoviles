package upc.edu.LoggyAPI.order_state.service;

import upc.edu.LoggyAPI.order_state.model.Batch;

import java.util.List;

public interface BatchService {
    Batch createBatch(Batch batch);
    Batch getBatchById(Long id);
    List<Batch> getAllBatches();
    Batch updateBatch(Long batch_id, Batch batch);
    Boolean deleteBatch(Long id);

}

package upc.edu.LoggyAPI.order_state.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.order_state.model.Batch;
import upc.edu.LoggyAPI.order_state.repository.BatchRepository;
import upc.edu.LoggyAPI.order_state.service.BatchService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    BatchRepository batchRepository;

    @Transactional
    @Override
    public Batch createBatch(Batch batch) {
        validateBatch(batch);
        existBatchByName(batch);
        return batchRepository.save(batch);
    }

    @Override
    public Batch getBatchById(Long id) {
        existBatchById(id);
        return batchRepository.findById(id).get();
    }

    @Override
    public List<Batch> getAllBatches() {
        List<Batch> batches = batchRepository.findAll();
        if(batches.isEmpty()){
            throw new ResourceNotFoundException("Unregistered batches");
        }
        return batches;
    }

    @Override
    public Batch updateBatch(Long batch_id, Batch batch) {
        existBatchById(batch_id);
        validateBatch(batch);
        existBatchByName(batch);
        Batch updateToBatch = batchRepository.findById(batch_id).get();
        updateToBatch.setName(batch.getName());
        updateToBatch.setExpireDate(batch.getExpireDate());
        updateToBatch.setExpireDate(batch.getProductionDate());
        return batchRepository.save(updateToBatch);
    }

    @Override
    public Boolean deleteBatch(Long id) {
        existBatchById(id);
        batchRepository.deleteById(id);
        return true;
    }

    private void validateBatch(Batch batch){
        if(batch.getExpireDate() == null) {
            throw new IllegalArgumentException("Batch expiration date is required");
        }
        if(batch.getExpireDate().isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Batch expiration date is not before a today");
        }
        if(batch.getProductionDate() == null){
            throw new IllegalArgumentException("Batch production date is required");
        }
        if(batch.getName() == null || batch.getName().isEmpty()){
            throw new IllegalArgumentException("Batch name is required");
        }
    }
    private void existBatchByName(Batch batch){
        if(batchRepository.existsByNameIgnoreCase(batch.getName())){
            throw new IllegalArgumentException(String.format("Batch whit name %s already exist", batch.getName()));
        }
    }
    private void existBatchById(Long id){
        if(!batchRepository.existsById(id)){
            throw new ResourceNotFoundException(String.format("Batch with id %s not found",id));
        }
    }
}

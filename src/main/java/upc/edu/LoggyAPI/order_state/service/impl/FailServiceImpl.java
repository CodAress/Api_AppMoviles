package upc.edu.LoggyAPI.order_state.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.order_state.model.Fail;
import upc.edu.LoggyAPI.order_state.repository.FailRepository;
import upc.edu.LoggyAPI.order_state.service.FailService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class FailServiceImpl implements FailService {
    @Autowired
    FailRepository failRepository;

    @Transactional
    @Override
    public Fail createFail(Fail fail) {
        validateFail(fail);
        return failRepository.save(fail);
    }
    @Transactional
    @Override
    public Fail getFailById(Long id) {
        existFailById(id);
        return failRepository.findById(id).get();
    }

    @Transactional
    @Override
    public List<Fail> getAllFails() {
        List<Fail> fails = failRepository.findAll();
        if(fails.isEmpty()){
            throw new ResourceNotFoundException("Unregistered fails");
        }
        return fails;
    }

    @Transactional
    @Override
    public Fail updteFail(Long id, Fail fail) {
        existFailById(id);
        validateFail(fail);
        Fail failToUpdate = failRepository.getReferenceById(id);
        failToUpdate.setType(fail.getType());
        failToUpdate.setCount(fail.getCount());
        return failRepository.save(failToUpdate);
    }

    @Transactional
    @Override
    public Boolean deleteFail(Long id) {
        existFailById(id);
        failRepository.deleteById(id);
        return true;
    }

    private void validateFail(Fail fail){
        if(fail.getType() == null){
            throw new IllegalArgumentException("Fail type is required");
        }
        if (fail.getType() < 0) {
            throw new IllegalArgumentException("Fail type can not negative");
        }
        if(fail.getCount() == null){
            throw new IllegalArgumentException("Fail count is required");
        }
        if(fail.getCount() < 0){
            throw new IllegalArgumentException("Fail count can not negative");
        }
    }
    private void existFailById(Long id){
        if(!failRepository.existsById(id)){
            throw new ResourceNotFoundException(String.format("Fail with id %s not found", id));
        }
    }
}

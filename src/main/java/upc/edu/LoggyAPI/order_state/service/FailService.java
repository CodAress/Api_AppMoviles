package upc.edu.LoggyAPI.order_state.service;

import upc.edu.LoggyAPI.order_state.model.Fail;

import java.util.List;

public interface FailService {
    Fail createFail(Fail fail);
    Fail getFailById(Long id);
    List<Fail> getAllFails();
    Fail updteFail(Long id, Fail fail);
    Boolean deleteFail(Long id);
}

package upc.edu.LoggyAPI.product.service;

import upc.edu.LoggyAPI.product.model.Measurement;

import java.util.List;

public interface MeasurementService {
    Measurement createMeasurement(Measurement measurement);
    Measurement updateMeasurement(Long measurement_id, Measurement measurement);
    Measurement getMeasurementById(Long measurement_id);
    List<Measurement> getAllMeasurement();
    boolean deleteMeasurement(Long id);
}

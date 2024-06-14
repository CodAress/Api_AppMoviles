package upc.edu.LoggyAPI.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.product.model.Measurement;
import upc.edu.LoggyAPI.product.repository.MeasurementRepository;
import upc.edu.LoggyAPI.product.service.MeasurementService;
import upc.edu.LoggyAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    public Measurement createMeasurement(Measurement measurement) {
        validateMeasurement(measurement);
        existMeasurementByName(measurement);
        return measurementRepository.save(measurement);
    }

    @Override
    public Measurement updateMeasurement(Long measurement_id, Measurement measurement) {
        existMeasurementById(measurement_id);
        validateMeasurement(measurement);
        existMeasurementByName(measurement);
        Measurement measurementToUpdate = measurementRepository.findById(measurement_id).get();
        measurementToUpdate.setName(measurement.getName());
        measurementToUpdate.setQuantity(measurement.getQuantity());
        return measurementRepository.save(measurementToUpdate);
    }

    @Override
    public Measurement getMeasurementById(Long measurement_id) {
        existMeasurementById(measurement_id);
        return measurementRepository.findById(measurement_id).get();
    }

    @Override
    public List<Measurement> getAllMeasurement() {
        List<Measurement> measurements = measurementRepository.findAll();
        if(measurements.isEmpty()){
            throw new ResourceNotFoundException("Unregistered measurements");
        }
        return measurements;
    }

    @Override
    public boolean deleteMeasurement(Long id) {
        existMeasurementById(id);
        measurementRepository.deleteById(id);
        return true;
    }

    private void validateMeasurement(Measurement measurement){
        if(measurement.getName() == null || measurement.getName().isEmpty()){
            throw new IllegalArgumentException("Measurement name is required");
        }
        if(measurement.getQuantity() == null){
            throw new IllegalArgumentException("Measurement quantity is required");
        }
        if(measurement.getQuantity() < 0){
            throw new IllegalArgumentException("Measurement quantity is not negative");
        }
    }

    private void existMeasurementByName(Measurement measurement){
        if(measurementRepository.existsByNameIgnoreCase(measurement.getName())){
            throw new ResourceNotFoundException(String.format("Measurement with name %s not found", measurement.getName()));
        }
    }

    private void existMeasurementById(Long measurement_id){
        if(!measurementRepository.existsById(measurement_id)){
            throw new ResourceNotFoundException(String.format("Measurement with id %s not found",measurement_id));
        }
    }
}

package upc.edu.LoggyAPI.product.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.MeasurementRequest;
import upc.edu.LoggyAPI.product.dto.MeasurementResponse;
import upc.edu.LoggyAPI.product.dto.mapper.MeasurementMapper;
import upc.edu.LoggyAPI.product.service.MeasurementService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @Transactional
    @PostMapping("/measurements")
    public ResponseEntity<MeasurementResponse> createMeasurement(@RequestBody MeasurementRequest measurementRequest){
        var measurement = MeasurementMapper.INSTANCE.measurementRequestToMeasurement(measurementRequest);
        var measurementResponse = MeasurementMapper.INSTANCE.measurementToMeasurementResponse(measurementService.createMeasurement(measurement));
        return new ResponseEntity<MeasurementResponse>(measurementResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/measurements/{id}")
    public ResponseEntity<MeasurementResponse> getMeasurementById(@PathVariable("id") Long id){
        var measurementResponse = MeasurementMapper.INSTANCE.measurementToMeasurementResponse(measurementService.getMeasurementById(id));
        return new ResponseEntity<MeasurementResponse>(measurementResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/measurements")
    public ResponseEntity<List<MeasurementResponse>> getAllMeasurements(){
        var measurementsResponses = MeasurementMapper.INSTANCE.measurementsToMeasurementsResponses(measurementService.getAllMeasurement());
        return new ResponseEntity<List<MeasurementResponse>>(measurementsResponses, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/measurements/{id}")
    public ResponseEntity<MeasurementResponse> updateMeasurement(@PathVariable("id") Long id, @RequestBody MeasurementRequest measurementRequest){
        var measurement = MeasurementMapper.INSTANCE.measurementRequestToMeasurement(measurementRequest);
        var measurementResponse = MeasurementMapper.INSTANCE.measurementToMeasurementResponse(measurementService.updateMeasurement(id,measurement));
        return new ResponseEntity<MeasurementResponse>(measurementResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/measurements/{id}")
    public ResponseEntity<Boolean> deleteMeasurement(@PathVariable("id") Long id){
        var valor = measurementService.deleteMeasurement(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

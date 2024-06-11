package upc.edu.LoggyAPI.product.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.product.dto.MeasurementRequest;
import upc.edu.LoggyAPI.product.dto.MeasurementResponse;
import upc.edu.LoggyAPI.product.model.Measurement;

import java.util.List;

@Mapper
public interface MeasurementMapper {
    MeasurementMapper INSTANCE = Mappers.getMapper(MeasurementMapper.class);
    Measurement measurementRequestToMeasurement(MeasurementRequest measurementRequest);
    MeasurementResponse measurementToMeasurementResponse(Measurement measurement);
    List<MeasurementResponse> measurementsToMeasurementsResponses(List<Measurement> measurements);
}

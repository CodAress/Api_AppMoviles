package upc.edu.LoggyAPI.line.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.LoggyAPI.line.dto.LineRequest;
import upc.edu.LoggyAPI.line.dto.LineResponse;
import upc.edu.LoggyAPI.line.model.Line;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LineMapper {
    LineMapper INSTANCE = Mappers.getMapper(LineMapper.class);
    Line lineRequestToLine(LineRequest lineRequest);
    LineResponse lineToLineResponse(Line line);
    List<LineResponse> linesToLinesResponse(List<Line> lines);

}

package upc.edu.LoggyAPI.line.service;

import upc.edu.LoggyAPI.line.model.Line;

import java.util.List;

public interface LineService {
    Line createLine(Line line);
    Line getLineById(Long id);
    List<Line> getAllLines();
    Line updateLine(Long id, Line line);
    boolean deleteLine(Long id);
}

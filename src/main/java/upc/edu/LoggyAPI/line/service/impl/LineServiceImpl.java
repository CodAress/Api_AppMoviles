package upc.edu.LoggyAPI.line.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.LoggyAPI.line.model.Line;
import upc.edu.LoggyAPI.line.repository.LineRepository;
import upc.edu.LoggyAPI.line.service.LineService;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineRepository lineRepository;

    @Override
    public Line createLine(Line line) {
        validateLine(line);
        existLineByName(line);
        return lineRepository.save(line);
    }

    @Override
    public Line getLineById(Long id) {
        existLineById(id);
        return lineRepository.findById(id).get();
    }

    @Override
    public List<Line> getAllLines() {
        List<Line> lines = lineRepository.findAll();
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Unregistered lines");
        }
        return lines;
    }

    @Override
    public Line updateLine(Long id, Line line) {
        existLineById(id);
        validateLine(line);
        existLineByName(line);
        Line lineToUpdate = lineRepository.findById(id).get();
        lineToUpdate.setName(line.getName());
        lineToUpdate.setDescription(line.getDescription());
        lineToUpdate.setImage(line.getImage());
        return lineRepository.save(lineToUpdate);
    }

    @Override
    public boolean deleteLine(Long id) {
        existLineById(id);
        lineRepository.deleteById(id);
        return true;
    }
    private void validateLine(Line line) {
        if (line.getName() == null || line.getName().isEmpty()) {
            throw new IllegalArgumentException("Line name is required");
        }
        if(line.getDescription() == null || line.getDescription().isEmpty()){
            throw new IllegalArgumentException("Line description is required");
        }
        if(line.getImage() == null || line.getImage().isEmpty()){
            throw new IllegalArgumentException("Line image is required");
        }
    }
    private void existLineByName(Line line) {
        if (lineRepository.existsByNameIgnoreCase(line.getName())) {
            throw new IllegalArgumentException("The line with name " + line.getName() + " already exists");
        }
    }
    private void existLineById(Long id) {
        if (!lineRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("The line with id %s does not exist", id));
        }
    }
}

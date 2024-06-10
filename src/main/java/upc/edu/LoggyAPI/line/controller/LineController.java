package upc.edu.LoggyAPI.line.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.line.dto.LineRequest;
import upc.edu.LoggyAPI.line.dto.LineResponse;
import upc.edu.LoggyAPI.line.dto.mapper.LineMapper;
import upc.edu.LoggyAPI.line.service.LineService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class LineController {
    @Autowired
    private LineService lineService;

    @Transactional
    @PostMapping(value = "/lines")
    public ResponseEntity<LineResponse> createLine(@RequestBody LineRequest lineRequest){
        var line = LineMapper.INSTANCE.lineRequestToLine(lineRequest);
        var lineResponse = LineMapper.INSTANCE.lineToLineResponse(lineService.createLine(line));
        return new ResponseEntity<LineResponse>(lineResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/lines/{id}")
    public ResponseEntity<LineResponse> getLineById(@PathVariable("id") Long id){
        var lineResponse = LineMapper.INSTANCE.lineToLineResponse(lineService.getLineById(id));
        return new ResponseEntity<LineResponse>(lineResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/lines")
    public ResponseEntity<List<LineResponse>> getAllLines(){
        var linesResponse = LineMapper.INSTANCE.linesToLinesResponse(lineService.getAllLines());
        return new ResponseEntity<List<LineResponse>>(linesResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/lines/{id}")
    public ResponseEntity<LineResponse> updateLine(@PathVariable("id") Long id, @RequestBody LineRequest lineRequest){
        var line = LineMapper.INSTANCE.lineRequestToLine(lineRequest);
        var lineResponse = LineMapper.INSTANCE.lineToLineResponse(lineService.updateLine(id, line));
        return new ResponseEntity<LineResponse>(lineResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/lines/{id}")
    public ResponseEntity<Boolean> deleteLine(@PathVariable("id") Long id){
        var valor = lineService.deleteLine(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

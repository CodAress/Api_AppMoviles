package upc.edu.LoggyAPI.line.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Gestión de Líneas de Producción", description = "Controlador para operaciones relacionadas con líneas de producción")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class LineController {
    @Autowired
    private LineService lineService;
    @Operation(summary = "Crea una nueva línea de producción", description = "Este endpoint crea una nueva línea de producción y devuelve los detalles de la nueva línea de producción creada.")
    @Transactional
    @PostMapping(value = "/lines")
    public ResponseEntity<LineResponse> createLine(@RequestBody LineRequest lineRequest){
        var line = LineMapper.INSTANCE.lineRequestToLine(lineRequest);
        var lineResponse = LineMapper.INSTANCE.lineToLineResponse(lineService.createLine(line));
        return new ResponseEntity<LineResponse>(lineResponse, HttpStatus.CREATED);
    }
    @Operation(summary = "Obtiene una línea de producción por su ID", description = "Este endpoint devuelve los detalles de una línea de producción por su id.")
    @Transactional
    @GetMapping(value = "/lines/{id}")
    public ResponseEntity<LineResponse> getLineById(@PathVariable("id") Long id){
        var lineResponse = LineMapper.INSTANCE.lineToLineResponse(lineService.getLineById(id));
        return new ResponseEntity<LineResponse>(lineResponse, HttpStatus.OK);
    }
    @Operation(summary = "Obtiene todas las líneas de producción", description = "Este endpoint devuelve los detalles de todas las líneas de producción.")
    @Transactional
    @GetMapping(value = "/lines")
    public ResponseEntity<List<LineResponse>> getAllLines(){
        var linesResponse = LineMapper.INSTANCE.linesToLinesResponse(lineService.getAllLines());
        return new ResponseEntity<List<LineResponse>>(linesResponse, HttpStatus.OK);
    }
    @Operation(summary = "Actualiza una línea de producción por su ID", description = "Este endpoint actualiza una línea de producción y devuelve los detalles de la línea de producción actualizada.")
    @Transactional
    @PutMapping(value = "/lines/{id}")
    public ResponseEntity<LineResponse> updateLine(@PathVariable("id") Long id, @RequestBody LineRequest lineRequest){
        var line = LineMapper.INSTANCE.lineRequestToLine(lineRequest);
        var lineResponse = LineMapper.INSTANCE.lineToLineResponse(lineService.updateLine(id, line));
        return new ResponseEntity<LineResponse>(lineResponse, HttpStatus.OK);
    }
    @Operation(summary = "Elimina una línea de producción por su ID", description = "Este endpoint elimina una línea de producción y devuelve un booleano indicando si se eliminó o no.")
    @Transactional
    @DeleteMapping(value = "/lines/{id}")
    public ResponseEntity<Boolean> deleteLine(@PathVariable("id") Long id){
        var valor = lineService.deleteLine(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

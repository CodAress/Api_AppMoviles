package upc.edu.LoggyAPI.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.LoggyAPI.product.dto.DescriptionRequest;
import upc.edu.LoggyAPI.product.dto.DescriptionResponse;
import upc.edu.LoggyAPI.product.dto.mapper.DescriptionMapper;
import upc.edu.LoggyAPI.product.service.DescriptionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class DescriptionController {
    @Autowired
    private DescriptionService descriptionService;

    @Transactional
    @PostMapping("/descriptions")
    public ResponseEntity<DescriptionResponse> createDescription(@RequestBody DescriptionRequest descriptionRequest){
        var description = DescriptionMapper.INSTANCE.descriptionRequestToDescription(descriptionRequest);
        var descriptionResponse = DescriptionMapper.INSTANCE.descriptionToDescriptionResponse(descriptionService.createDescription(description));
        return new ResponseEntity<DescriptionResponse>(descriptionResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping("/descriptions/{id}")
    public ResponseEntity<DescriptionResponse> getDescriptionById(@PathVariable("id") Long id){
        var descriptionResponse = DescriptionMapper.INSTANCE.descriptionToDescriptionResponse(descriptionService.getDescriptionById(id));
        return new ResponseEntity<DescriptionResponse>(descriptionResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/descriptions")
    public ResponseEntity<List<DescriptionResponse>> getAllDescriptions(){
        var descriptionsResponse = DescriptionMapper.INSTANCE.descriptionsToDescriptionResponses(descriptionService.getAllDescriptions());
        return new ResponseEntity<List<DescriptionResponse>>(descriptionsResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/descriptions/{id}")
    public ResponseEntity<DescriptionResponse> updateDescription(@PathVariable("id") Long id, @RequestBody DescriptionRequest descriptionRequest){
        var description = DescriptionMapper.INSTANCE.descriptionRequestToDescription(descriptionRequest);
        var descriptionResponse = DescriptionMapper.INSTANCE.descriptionToDescriptionResponse(descriptionService.updateDescription(id, description));
        return new ResponseEntity<DescriptionResponse>(descriptionResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/descriptions/{id}")
    public ResponseEntity<Boolean> deleteDescription(@PathVariable("id") Long id){
        var valor = descriptionService.deleteDescription(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}

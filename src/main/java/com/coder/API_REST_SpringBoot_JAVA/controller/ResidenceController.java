package com.coder.API_REST_SpringBoot_JAVA.controller;

import com.coder.API_REST_SpringBoot_JAVA.models.Residence;
import com.coder.API_REST_SpringBoot_JAVA.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residence")
public class ResidenceController {
    @Autowired
    private ResidenceService residenceService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object addResidence(@RequestBody Residence residence){
        try {
            Residence createResidence = residenceService.saveResidence(residence);
            return ResponseEntity.status(HttpStatus.CREATED).body(createResidence);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}

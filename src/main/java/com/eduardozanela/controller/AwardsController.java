package com.eduardozanela.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eduardozanela.dto.AwardsDTO;
import com.eduardozanela.dto.ResponseDTO;
import com.eduardozanela.service.AwardsService;

@RestController
public class AwardsController {

    @Autowired
    private AwardsService service;
    
    @GetMapping(value = "/v1/awards")
    public ResponseEntity<List<AwardsDTO>> getAllAwards(){
        return service.getAllAwards();
    }
    
    @GetMapping(value = "/v1/awards/{id}")
    public ResponseEntity<AwardsDTO> findById(@PathVariable("id") Integer id){
        return service.findAwardsById(id);
    }
    
    @GetMapping(value = "/v1/winners")
    public ResponseEntity<ResponseDTO> filterAwardsWinners(){
        return service.filterAwards();
    }

}

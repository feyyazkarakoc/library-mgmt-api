package com.tpe.controller;

import com.tpe.dto.OwnerDTO;
import com.tpe.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // http://localhost:8080/owners/save + JSON + POST
    @PostMapping("/save")
    public ResponseEntity<String> saveOwner(@Valid @RequestBody OwnerDTO ownerDTO){
        ownerService.saveOwner(ownerDTO);
        return new ResponseEntity<>("Owner was successfully saved.", HttpStatus.CREATED);
    }








}

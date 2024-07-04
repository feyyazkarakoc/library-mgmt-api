package com.tpe.controller;

import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import com.tpe.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // http://localhost:8080/owners + GET
    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners(){
        List<Owner> ownerList = ownerService.getAll();
        return ResponseEntity.ok(ownerList);
    }

    // http://localhost:8080/owners/ownerId/2 + GET
    @GetMapping("/ownerId/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable("id") Long id){
        Owner owner = ownerService.getOwnerById(id);
        return new ResponseEntity<>(owner,HttpStatus.OK);
    }

    // http://localhost:8080/owners/2 + GET
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerDTOById(@PathVariable("id") Long id){
        OwnerDTO ownerDTO = ownerService.getOwnerDTOById(id);
        return ResponseEntity.ok(ownerDTO);
    }








}

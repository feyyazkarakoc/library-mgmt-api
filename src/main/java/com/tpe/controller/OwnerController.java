package com.tpe.controller;

import com.tpe.domain.Book;
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

    //Save an Owner
    //http://localhost:8080/owners/save + JSON + POST
    @PostMapping("/save")
    public ResponseEntity<String> saveOwner(@Valid @RequestBody OwnerDTO ownerDTO){
        ownerService.saveOwner(ownerDTO);
        return new ResponseEntity<>("Owner was successfully saved.", HttpStatus.CREATED);
    }

    //Find All Owners
    //http://localhost:8080/owners + GET
    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners(){
        List<Owner> ownerList = ownerService.getAll();
        return ResponseEntity.ok(ownerList);
    }

    //Find an Owner By ID
    //http://localhost:8080/owners/ownerId/2 + GET
    @GetMapping("/ownerId/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable("id") Long id){
        Owner owner = ownerService.getOwnerById(id);
        return new ResponseEntity<>(owner,HttpStatus.OK);
    }

    //Find an OwnerDTO By ID
    //http://localhost:8080/owners/2 + GET
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerDTOById(@PathVariable("id") Long id){
        OwnerDTO ownerDTO = ownerService.getOwnerDTOById(id);
        return ResponseEntity.ok(ownerDTO);
    }

    //Delete an Owner By ID
    //http://localhost:8080/owners/2 + DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable("id") Long id){
        ownerService.deleteOwnerById(id);
        return ResponseEntity.ok("The owner was successfully deleted.");
    }

    //Update an Owner By ID
    //http://localhost:8080/owners/2 + PUT + JSON
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOwner(@PathVariable("id") Long id,@RequestBody OwnerDTO ownerDTO){
        ownerService.updateOwner(id,ownerDTO);
        return ResponseEntity.ok("The owner was successfully updated.");
    }

    //list all books of an owner which is given ID
    //http://localhost:8080/owners/books/2 + GET
    @GetMapping("/books/{id}")
    public ResponseEntity<List<Book>> allBooksByOwnerId(@PathVariable("id") Long ownerId){
        List<Book> bookList = ownerService.allBooksByOwnerId(ownerId);
        return ResponseEntity.ok(bookList) ;
    }








}

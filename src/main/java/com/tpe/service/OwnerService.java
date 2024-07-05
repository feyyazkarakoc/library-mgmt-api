package com.tpe.service;


import com.tpe.domain.Book;
import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import com.tpe.exceptions.ConflictException;
import com.tpe.exceptions.ResourceNotFoundException;
import com.tpe.repository.BookRepository;
import com.tpe.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BookRepository bookRepository;

    public void saveOwner(OwnerDTO ownerDTO) {
        Boolean isExist = ownerRepository.existsByEmail(ownerDTO.getEmail());
        if (isExist) {
            throw new ConflictException("Email is already exist.");
        }

        Owner newOwner = new Owner(ownerDTO);
        ownerRepository.save(newOwner);

        /*  Owner newOwner = new Owner();
        newOwner.setName(ownerDTO.getName());
        newOwner.setLastName(ownerDTO.getLastName());
        newOwner.setPhoneNumber(ownerDTO.getPhoneNumber());
        newOwner.setEmail(ownerDTO.getEmail());
        ownerRepository.save(newOwner); */
    }


    public List<Owner> getAll() {
        List<Owner> ownerList = ownerRepository.findAll();
        if (ownerList.isEmpty()) {
            throw new ResourceNotFoundException("No owners were found.");
        }
        return ownerList;
    }


    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Owner not found by ID : " + id));
    }


    public OwnerDTO getOwnerDTOById(Long id) {
        Owner existingOwner = getOwnerById(id);
        return new OwnerDTO(existingOwner);
    }

    public void deleteOwnerById(Long id) {
        Owner existingOwner = getOwnerById(id);
        for (Book book : existingOwner.getBookList()) {
            book.setOwner(null);
            bookRepository.save(book);
        }
        ownerRepository.delete(existingOwner);
    }

    public void updateOwner(Long id, OwnerDTO ownerDTO) {
        Owner existingOwner = getOwnerById(id);
        boolean emailExist = ownerRepository.existsByEmail(ownerDTO.getEmail());
        if (emailExist && !ownerDTO.getEmail().equals(existingOwner.getEmail())) {
            throw new ConflictException("Email is already exist.");
        }
        existingOwner.setName(ownerDTO.getName());
        existingOwner.setLastName(ownerDTO.getLastName());
        existingOwner.setPhoneNumber(ownerDTO.getPhoneNumber());
        existingOwner.setEmail(ownerDTO.getEmail());
        ownerRepository.save(existingOwner);
    }

    public List<Book> allBooksByOwnerId(Long ownerId) {
        Owner existingOwner = getOwnerById(ownerId);
        List<Book> bookList = existingOwner.getBookList();
        if (bookList.isEmpty()) {
            throw new ResourceNotFoundException("No books found!");
        }
        return bookList;

    }
}

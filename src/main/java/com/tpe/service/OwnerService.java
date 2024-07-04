package com.tpe.service;


import com.tpe.domain.Owner;
import com.tpe.dto.OwnerDTO;
import com.tpe.exceptions.ConflictException;
import com.tpe.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public void saveOwner(OwnerDTO ownerDTO) {
        Boolean isExist = ownerRepository.existsByEmail(ownerDTO.getEmail());
        if (isExist){
            throw new ConflictException("Email is already exist.");
        }

        Owner newOwner = new Owner();
        newOwner.setName(ownerDTO.getName());
        newOwner.setLastName(ownerDTO.getLastName());
        newOwner.setPhoneNumber(ownerDTO.getPhoneNumber());
        newOwner.setEmail(ownerDTO.getEmail());
        ownerRepository.save(newOwner);
    }
}

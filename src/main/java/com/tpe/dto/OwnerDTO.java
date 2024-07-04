package com.tpe.dto;

import com.tpe.domain.Owner;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OwnerDTO {

    @NotBlank(message = "Please provide a valid name!")
    private String name;

    @NotBlank(message = "Please provide a valid last name!")
    private String lastName;

    private String phoneNumber;

    @Email(message = "Please provide a valid email!")
    private String email;

    public OwnerDTO(Owner owner) {
        this.name = owner.getName();
        this.lastName = owner.getLastName();
        this.phoneNumber = owner.getPhoneNumber();
        this.email = owner.getEmail();
    }


}

package com.tpe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDTO {

    @NotBlank(message = "Please provide a valid name!")
    private String name;

    @NotBlank(message = "Please provide a valid last name!")
    private String lastName;

    private String phoneNumber;

    @Email(message = "Please provide a valid email!")
    private String email;


}

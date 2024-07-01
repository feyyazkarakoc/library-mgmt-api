package com.tpe.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {


    @NotBlank(message = "Book name cannot be blank!")
    @NotNull(message = "Book name must be provided.")
    private String title;


    @NotBlank(message = "Author name cannot be empty!")
    @Size(min = 2,max = 30,message ="Author name (${validatedValue})  must be between {min} and {max} characters!")
    private String author;

    private String publicationDate;
}

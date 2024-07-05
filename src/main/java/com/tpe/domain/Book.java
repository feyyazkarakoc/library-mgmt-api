package com.tpe.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "t_book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Book name cannot be blank!")
    @NotNull(message = "Book name must be provided.")
    @Column(nullable = false)
    private String title;


    @NotBlank(message = "Author name cannot be empty!")
    @Size(min = 2,max = 30,message ="Author name (${validatedValue})  must be between {min} and {max} characters!")
    @Column(length = 30,nullable = false)
    private String author;

    @Column(nullable = false)
    private String publicationDate;

    @ManyToOne
    @JsonIgnore
    private Owner owner;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}

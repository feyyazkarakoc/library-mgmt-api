package com.tpe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_owner")
@Getter
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;


    @NotBlank(message = "Please provide a valid name!")
    @Column(nullable = false,unique = true)
    private String name;

    @NotBlank(message = "Please provide a valid last name!")
    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    @Email(message = "Please provide a valid email!")
    @Column(nullable = false)
    private String email;

    @Setter(AccessLevel.NONE)
    private LocalDateTime registrationDate = LocalDateTime.now();

    @OneToMany(mappedBy = "owner")
    private List<Book> bookList = new ArrayList<>();
}

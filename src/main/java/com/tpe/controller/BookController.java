package com.tpe.controller;


import com.tpe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")//http://localhost:8080/books
public class BookController {

    @Autowired
    private BookService bookService;
}

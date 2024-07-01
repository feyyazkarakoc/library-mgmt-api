package com.tpe.service;


import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.exceptions.BookNotFoundException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long identity) {
        return bookRepository.findById(identity).
                orElseThrow(()-> new BookNotFoundException("Book not found by ID : "+identity));
    }

    public void deleteBookById(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }


    public List<Book> filterBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }


    public void updateBookById(Long id, BookDTO bookDTO) {
        Book existingBook = getBookById(id);
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setPublicationDate(bookDTO.getPublicationDate());
        bookRepository.save(existingBook);
    }
}

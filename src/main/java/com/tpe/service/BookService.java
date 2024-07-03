package com.tpe.service;


import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.exceptions.BookNotFoundException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                orElseThrow(() -> new BookNotFoundException("Book not found by ID : " + identity));
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

    public Page<Book> getAllBookWithPage(Pageable pageable) {

        return bookRepository.findAll(pageable);
    }


    public List<Book> getBooksByAuthor(String author) {
        List<Book> bookList = bookRepository.findByAuthorWithJPQL(author);
        if (bookList.isEmpty()) {
            throw new BookNotFoundException("No books found for the given author!");
        }
        return bookList;
    }

    public List<Book> findBooksByAuthorAndPubDate(String author, String pubDate) {
        return bookRepository.findByAuthorAndPublicationDate(author, pubDate);
    }


    public List<Book> findBooksByAuthorAndPubDateWithNativeSQL(String author, String pubDate) {
        return bookRepository.findByAuthorAndPublicationDateWithNativeSQL(author, pubDate);
    }

    public List<Book> findBooksByAuthorAndPubDateWithHQL(String author, String pubDate) {
        return bookRepository.findByAuthorAndPublicationDateWithHQL(author, pubDate);

    }
}

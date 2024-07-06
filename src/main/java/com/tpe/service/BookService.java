package com.tpe.service;


import com.tpe.domain.Book;
import com.tpe.domain.Owner;
import com.tpe.dto.BookDTO;
import com.tpe.exceptions.ConflictException;
import com.tpe.exceptions.ResourceNotFoundException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    @Lazy
    private OwnerService ownerService;


    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long identity) {
        return bookRepository.findById(identity).
                orElseThrow(() -> new ResourceNotFoundException("Book not found by ID : " + identity));
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
            throw new ResourceNotFoundException("No books found!");
        }
        return bookList;
    }

    public List<Book> findBooksByAuthorAndPubDate(String author, String pubDate) {
        List<Book> bookList = bookRepository.findByAuthorAndPublicationDate(author, pubDate);
        if (bookList.isEmpty()) {
            throw new ResourceNotFoundException("No books found!");
        }
        return bookList;
    }


    public List<Book> findBooksByAuthorAndPubDateWithNativeSQL(String author, String pubDate) {
        List<Book> bookList = bookRepository.findByAuthorAndPublicationDateWithNativeSQL(author, pubDate);
        if (bookList.isEmpty()) {
            throw new ResourceNotFoundException("No books found!");
        }
        return bookList;
    }

    public List<Book> findBooksByAuthorAndPubDateWithHQL(String author, String pubDate) {
        List<Book> bookList = bookRepository.findByAuthorAndPublicationDateWithHQL(author, pubDate);
        if (bookList.isEmpty()) {
            throw new ResourceNotFoundException("No books found!");
        }
        return bookList;

    }

    public List<Book> getBooksByTitleWithPattern(String searchPattern) {
        searchPattern = "%" + searchPattern + "%";
        List<Book> bookList = bookRepository.findBooksByTitleWithPattern(searchPattern);
        if (bookList.isEmpty()) {
            throw new ResourceNotFoundException("No books found!");
        }
        return bookList;
    }


    public void addBookToOwner(Long bookID, Long ownerID) {

       /* Book foundBook = getBookById(bookID);
        Owner foundOwner = ownerService.getOwnerById(ownerID);
        if (foundBook.getOwner() != null){
            boolean isBookExist = foundBook.getOwner().getId().equals(foundOwner.getId());
            if (isBookExist){
                throw new ConflictException("This book already exists in the user's list!");
            }else {
                throw new ConflictException("This book is already in another user's list!");
            }
        }
        foundBook.setOwner(foundOwner);
        bookRepository.save(foundBook);*/


        Book foundBook = getBookById(bookID);
        Owner foundOwner = ownerService.getOwnerById(ownerID);
        boolean isBookExist = foundOwner.getBookList().stream().
                anyMatch(book -> book.getId().equals(foundBook.getId()));

        if (isBookExist) {
            throw new ConflictException("This book already exists in the user's list!");
        } else if (foundBook.getOwner() != null) {
            throw new ConflictException("This book is already in another user's list!");
        } else {
            foundBook.setOwner(foundOwner);
            bookRepository.save(foundBook);
        }


    }
}

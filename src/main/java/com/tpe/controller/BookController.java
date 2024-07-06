package com.tpe.controller;


import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")//http://localhost:8080/books
public class BookController {

    @Autowired
    private BookService bookService;


    //Save a Book
    // http://localhost:8080/books + POST + JSON
    @PostMapping
    public ResponseEntity<String> saveBook(@Valid @RequestBody Book book) {
        bookService.saveBook(book);
        return new ResponseEntity<>("The book was successfully registered.", HttpStatus.CREATED);
    }


    //Get All Books
    // http://localhost:8080/books + GET
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = bookService.getAll();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    //Get a Book by its ID
    // http://localhost:8080/books/2 + GET
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long identity) {
        Book found = bookService.getBookById(identity);
        return ResponseEntity.ok(found);
    }

    //Delete a Book by its ID
    // http://localhost:8080/books/2 + DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("The book was successfully deleted.");
    }

    //Get a Book by its ID with RequestParam
    // http://localhost:8080/books/q?id=2 + GET
    @GetMapping("/q")
    public ResponseEntity<Book> getBookByIdWithQuery(@RequestParam("id") Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    //Get a Book by its Title with RequestParam
    //http://localhost:8080/books/search?title=Martin Eden + GET
    @GetMapping("/search")
    public ResponseEntity<List<Book>> filterBooksByTitle(@RequestParam("title") String title) {
        List<Book> bookList = bookService.filterBooksByTitle(title);
        return ResponseEntity.ok(bookList);
    }


    //Update a Book With Using DTO
    // http://localhost:8080/books/update/2 +PUT + JSON
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookDTO bookDTO) {
        bookService.updateBookById(id, bookDTO);
        return ResponseEntity.ok("The book was successfully updated.");
    }


    //Get Books With Page
    // http://localhost:8080/books/s?page=1&size=2&sort=publicationDate&direction=ASC + GET
    @GetMapping("/s")
    public ResponseEntity<Page<Book>> getAllBooksWithpage(@RequestParam("page") int page,
                                                          @RequestParam("size") int size,
                                                          @RequestParam("sort") String sortBy,
                                                          @RequestParam("direction") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(direction, sortBy));
        Page<Book> bookWithPagge = bookService.getAllBookWithPage(pageable);

        return ResponseEntity.ok(bookWithPagge);
    }


    //Get a Book By Its Author Using JPQL
    //http://localhost:8080/books/a?author=Tolstoy
    @GetMapping("/a")
    public ResponseEntity<List<Book>> getBooksbyAuthor(@RequestParam("author") String author) {
        List<Book> bookList = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(bookList);
    }


    //Get Books By Its Author and PublicationDate With JPA
    //http://localhost:8080/books/filter?author=Tolstoy&pubDate=1900 + GET
    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterByAuthorAndPublicationDate(@RequestParam("author") String author, @RequestParam("pubDate") String pubDate) {
        List<Book> bookList = bookService.findBooksByAuthorAndPubDate(author, pubDate);
        return ResponseEntity.ok(bookList);

    }

    //Get Books By Its Author and PublicationDate With Native SQL
    //http://localhost:8080/books/filter/native?author=Tolstoy&pubDate=1900 + GET
    @GetMapping("/filter/native")
    public ResponseEntity<List<Book>> filterByAuthorAndPublicationDateWithNativeSQL(@RequestParam("author") String author, @RequestParam("pubDate") String pubDate) {
        List<Book> bookList = bookService.findBooksByAuthorAndPubDateWithNativeSQL(author, pubDate);
        return ResponseEntity.ok(bookList);

    }


    //Get Books By Its Author and PublicationDate With HQL
    //http://localhost:8080/books/search/a?author=Tolstoy&pubDate=1900 + GET
    @GetMapping("/search/a")
    public ResponseEntity<List<Book>> filterByAuthorAndPublicationDateWithHQL(@RequestParam("author") String author, @RequestParam("pubDate") String pubDate) {
        List<Book> bookList = bookService.findBooksByAuthorAndPubDateWithHQL(author, pubDate);
        return ResponseEntity.ok(bookList);

    }


    //Get Books By Its Title Which Contains a Pattern
    //http://localhost:8080/books/filterbook?searchpattern=Eden + GET
    @GetMapping("/filterbook")
    public ResponseEntity<List<Book>> getBooksByTitleWithPattern(@RequestParam("searchpattern") String searchPattern) {
        List<Book> bookList = bookService.getBooksByTitleWithPattern(searchPattern);
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }


    //Add a Book to an Owner
    //http://localhost:8080/books/add?book=3&owner=1 + PATCH
    @PatchMapping("/add")
    public ResponseEntity<String> addBookToOwner(@RequestParam("book") Long bookID,
                                                 @RequestParam("owner") Long ownerID) {
        bookService.addBookToOwner(bookID, ownerID);
        return ResponseEntity.ok("Book successfully assigned to owner.");
    }


}

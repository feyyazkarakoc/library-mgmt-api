package com.tpe.repository;

import com.tpe.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findByTitle(String title);

    @Query(value = "SELECT b FROM Book b WHERE b.author=:pAuthor")
    List<Book> findByAuthorWithJPQL(@Param("pAuthor") String author);

    List<Book> findByAuthorAndPublicationDate(String author, String pubDate);

    @Query(value = "SELECT * FROM t_book t WHERE t.author=:pAuthor AND t.publication_date=:pPubDate", nativeQuery = true)
    List<Book> findByAuthorAndPublicationDateWithNativeSQL(@Param("pAuthor") String author, @Param("pPubDate") String pubDate);


    @Query(value = "FROM Book b WHERE b.author=:pAuthor AND b.publicationDate=:pPubDate")
    List<Book> findByAuthorAndPublicationDateWithHQL(@Param("pAuthor") String author, @Param("pPubDate") String pubDate);

    @Query(value = "SELECT * FROM t_book  WHERE title ILIKE :pSearchPattern",nativeQuery = true)
    List<Book> findBooksByTitleWithPattern(@Param("pSearchPattern") String searchPattern);

}

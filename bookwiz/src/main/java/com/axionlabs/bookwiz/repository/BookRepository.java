package com.axionlabs.bookwiz.repository;

import com.axionlabs.bookwiz.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.bookTitle) = LOWER(:bookTitle) AND b.author = :author")
    Optional<Book> findBookByBookTitleAndAuthor(String bookTitle, String author);
}

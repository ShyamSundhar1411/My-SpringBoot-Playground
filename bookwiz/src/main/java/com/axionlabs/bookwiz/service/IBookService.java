package com.axionlabs.bookwiz.service;

import com.axionlabs.bookwiz.dto.book.BookCreateDto;
import com.axionlabs.bookwiz.dto.book.BookDto;

import java.util.List;

public interface IBookService {
    List<BookDto> fetchAllBooks();
    BookDto fetchBookByBookId(Long bookId);
    BookDto createBook(BookCreateDto bookData);
    BookDto updateBook(Long bookId, BookDto bookData);
    boolean deleteBook(Long bookId);
}

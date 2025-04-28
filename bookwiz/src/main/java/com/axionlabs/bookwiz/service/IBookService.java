package com.axionlabs.bookwiz.service;

import com.axionlabs.bookwiz.dto.book.BookCreateOrUpdateDto;
import com.axionlabs.bookwiz.dto.book.BookDto;
import jakarta.validation.Valid;

import java.util.List;

public interface IBookService {
    List<BookDto> fetchAllBooks();
    BookDto fetchBookByBookId(Long bookId);
    BookDto createBook(BookCreateOrUpdateDto bookData);
    BookDto updateBook(Long bookId, @Valid BookCreateOrUpdateDto bookData);
    boolean deleteBook(Long bookId);
}

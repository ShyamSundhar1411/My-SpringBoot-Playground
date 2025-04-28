package com.axionlabs.bookwiz.service;

import com.axionlabs.bookwiz.dto.BookCreateDto;
import com.axionlabs.bookwiz.dto.BookDto;

import java.util.List;

public interface IBookService {
    List<BookDto> fetchAllBooks();
    BookDto fetchBookByBookId(Long bookId);
    BookDto createBook(BookCreateDto bookData);
}

package com.axionlabs.bookwiz.service.impl;

import com.axionlabs.bookwiz.dto.BookDto;
import com.axionlabs.bookwiz.entity.Book;
import com.axionlabs.bookwiz.mapper.BookMapper;
import com.axionlabs.bookwiz.repository.BookRepository;
import com.axionlabs.bookwiz.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements IBookService {
    private BookRepository bookRepository;

    /**
     *
     * @return - List of Books
     */
    @Override
    public List<BookDto> fetchAllBooks() {
        return bookRepository.findAll().stream().map(
                book -> {
                    return BookMapper.mapToBookDto(new BookDto(),book);

                }
        ).toList();
    }
}

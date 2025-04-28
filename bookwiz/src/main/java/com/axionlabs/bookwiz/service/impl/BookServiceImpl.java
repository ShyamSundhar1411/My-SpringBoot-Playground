package com.axionlabs.bookwiz.service.impl;

import com.axionlabs.bookwiz.dto.BookDto;
import com.axionlabs.bookwiz.entity.Book;
import com.axionlabs.bookwiz.exception.BookAlreadyExistsException;
import com.axionlabs.bookwiz.exception.ResourceNotFoundException;
import com.axionlabs.bookwiz.mapper.BookMapper;
import com.axionlabs.bookwiz.repository.BookRepository;
import com.axionlabs.bookwiz.service.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookServiceImpl implements IBookService {
    private BookRepository bookRepository;

    /**
     *
     * @return List<BookDto> - List of Books
     */
    @Override
    public List<BookDto> fetchAllBooks() {
        return bookRepository.findAll().stream().map(
                book -> {
                    return BookMapper.mapToBookDto(new BookDto(),book);

                }
        ).toList();
    }
    /**
     *
     * @param bookId - ID of the Book
     * @return BookDto -  Book Detail by Id
     */
    @Override
    public BookDto fetchBookByBookId(Long bookId){
        Book book = bookRepository.findById(
                bookId
        ).orElseThrow( () -> new ResourceNotFoundException(
                "Book","id",bookId.toString()
                )
        );
        return BookMapper.mapToBookDto(new BookDto(),book);
    }

    /**
     *
     * @param bookData - Data of the book to be created
     * @return BookDto - Created Book
     */
    @Override
    public BookDto createBook(BookDto bookData){
        Optional<Book> optionalBook = bookRepository.findBookByBookTitleAndAuthor(bookData.getBookTitle(), bookData.getAuthor());
        if(optionalBook.isPresent()){
            throw new BookAlreadyExistsException(
                    String.format("Book already exists with the given title '%s' and author '%s'.",
                            bookData.getBookTitle(), bookData.getAuthor())
            );
        }
        Book book = bookRepository.save(BookMapper.mapToBookEntity(bookData,new Book()));
        return BookMapper.mapToBookDto(new BookDto(), book);
    }
}

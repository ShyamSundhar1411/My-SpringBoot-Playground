package com.axionlabs.bookwiz.service.impl;

import com.axionlabs.bookwiz.dto.book.BookCreateOrUpdateDto;
import com.axionlabs.bookwiz.dto.book.BookDto;
import com.axionlabs.bookwiz.entity.Book;
import com.axionlabs.bookwiz.exception.BookAlreadyExistsException;
import com.axionlabs.bookwiz.exception.ResourceNotFoundException;
import com.axionlabs.bookwiz.mapper.BookMapper;
import com.axionlabs.bookwiz.repository.BookRepository;
import com.axionlabs.bookwiz.service.IBookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public BookDto createBook(BookCreateOrUpdateDto bookData){
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

    /**
     *
     * @param bookId - ID of the Book
     * @param bookData - New Details of the book
     * @return BookDto - Updated Book
     */
    @Override
    public BookDto updateBook(Long bookId, @Valid BookCreateOrUpdateDto bookData) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Book","BookId",bookId.toString()
                )
        );
        BookMapper.mapToBookEntity(bookData,book);
        bookRepository.save(book);
        return BookMapper.mapToBookDto(new BookDto(),book);
    }

    /**
     *
     * @param bookId - ID of the book to be deleted
     * @return boolean indicating if delete has been performed successfully or not
     */
    @Override
    public boolean deleteBook(Long bookId) {
        bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Book","BookId",bookId.toString()
                )
        );
        bookRepository.deleteById(bookId);
        return true;
    }

    /**
     * @param bookData - List of books to be created
     * @return list of created books
     */
    @Transactional
    @Override
    public List<BookDto> bulkCreateBooks(List<BookCreateOrUpdateDto> bookData) {
        List<BookCreateOrUpdateDto> booksToCreate = bookData.stream()
                .filter(bookDto -> !bookExists(bookDto.getBookTitle(), bookDto.getAuthor()))
                .toList();


        if (booksToCreate.isEmpty()) {
            return List.of();
        }
        List<Book> books = booksToCreate.stream().map(
                bookDto -> BookMapper.mapToBookEntity(bookDto, new Book())
        ).toList();
        List<Book> savedBook = bookRepository.saveAll(books);
        return savedBook.stream().map(
                book -> BookMapper.mapToBookDto(new BookDto(), book)
        ).toList();
    }

    @Override
    public List<BookDto> searchBooks(String q) {
        return bookRepository.searchBooks(q).stream().map(
                book -> BookMapper.mapToBookDto(new BookDto(), book)
        ).toList();
    }

    private boolean bookExists(String title, String author) {
        Optional<Book> optionalBook = bookRepository.findBookByBookTitleAndAuthor(title, author);
        return optionalBook.isPresent();
    }

}

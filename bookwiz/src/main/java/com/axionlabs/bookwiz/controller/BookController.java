package com.axionlabs.bookwiz.controller;

import com.axionlabs.bookwiz.dto.book.BookCreateOrUpdateDto;
import com.axionlabs.bookwiz.dto.book.BookDto;
import com.axionlabs.bookwiz.dto.book.BookResponseDto;
import com.axionlabs.bookwiz.dto.ErrorResponseDto;
import com.axionlabs.bookwiz.service.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Book Management APIs - BookWiz",
        description = "Provides CRUD operations for managing books within the BookWiz application."
)
@RestController
@RequestMapping(path = "/api/v1",produces = (MediaType.APPLICATION_JSON_VALUE))
public class BookController {
    public final IBookService iBookService;

    public BookController(IBookService iBookService) {
        this.iBookService = iBookService;
    }
    @GetMapping("/books")

    @Operation(
            summary = "Retrieve all books",
            description = "Fetches a list of all books available in the BookWiz platform."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of books."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<List<BookDto>> fetchAllBooks() {
        List<BookDto> books = iBookService.fetchAllBooks();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/books/{id}")
    @Operation(
            summary = "Retrieve book by bookId",
            description = "Fetches a book available based on the book id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the book by bookId"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book Not Found",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    )
            }
    )
    public ResponseEntity<BookDto> fetchBookByBookId(@PathVariable Long id){
        BookDto book = iBookService.fetchBookByBookId(id);
        return ResponseEntity.ok(book);
    }
    @PostMapping("/books/create")
    @Operation(
            summary = "Create a new book",
            description = "Creates a new book entry in the BookWiz platform based on the provided book details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Book created successfully.",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data provided.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<BookResponseDto<BookDto>> createBook(@Valid @RequestBody BookCreateOrUpdateDto bookDto){
        BookDto book = iBookService.createBook(bookDto);
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                new BookResponseDto<BookDto>(
                        HttpStatus.CREATED,
                        "Book Created Successfully",
                        book
                )
        );
    }
    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponseDto<BookDto>> updateBook(@PathVariable Long id,@Valid @RequestBody BookCreateOrUpdateDto bookDto){
        BookDto book = iBookService.updateBook(id, bookDto);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new BookResponseDto<BookDto>(
                        HttpStatus.OK,
                        "Book Updated Successfully",
                        book
                )
        );

    }
}

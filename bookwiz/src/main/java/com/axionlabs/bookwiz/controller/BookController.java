package com.axionlabs.bookwiz.controller;

import com.axionlabs.bookwiz.dto.BaseResponseDto;
import com.axionlabs.bookwiz.dto.ErrorResponseDto;
import com.axionlabs.bookwiz.dto.book.BookCreateOrUpdateDto;
import com.axionlabs.bookwiz.dto.book.BookDto;
import com.axionlabs.bookwiz.dto.book.BookResponseDto;
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
import org.springframework.validation.annotation.Validated;
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
                    description = "Successfully retrieved the list of books.",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<BookResponseDto<List<BookDto>>> fetchAllBooks() {
        List<BookDto> books = iBookService.fetchAllBooks();
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new BookResponseDto<List<BookDto>>(
                        HttpStatus.OK,
                        "Books Retrieved Successfully",
                        books
                )
        );
    }
    @GetMapping("/books/{bookId}")
    @Operation(
            summary = "Retrieve book by bookId",
            description = "Fetches a book from the BookWiz platform based on the provided book ID."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved the book by bookId.",
                            content = @Content(schema = @Schema(implementation = BookResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Book Not Found with the provided bookId.",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error.",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    )
            }
    )
    public ResponseEntity<BookResponseDto<BookDto>> fetchBookByBookId(@PathVariable Long bookId){
        BookDto book = iBookService.fetchBookByBookId(bookId);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
          new BookResponseDto<BookDto>(
                  HttpStatus.OK,
                  "Book Fetched Successfully",
                  book
          )
        );
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
    @PutMapping("/books/{bookId}")
    @Operation(
            summary = "Update the existing book details",
            description = "Updates the details of an existing book in the BookWiz platform based on the provided book details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Book updated successfully.",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data provided.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Book not found with the provided id.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<BookResponseDto<BookDto>> updateBook(@PathVariable Long bookId,@Valid @RequestBody BookCreateOrUpdateDto bookDto){
        BookDto book = iBookService.updateBook(bookId, bookDto);
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

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<BaseResponseDto> deleteBook(@PathVariable Long bookId) {
        boolean isDeleted = iBookService.deleteBook(bookId);
        if(isDeleted) {
            return ResponseEntity.status(
                    HttpStatus.NO_CONTENT
            ).body(
                    new BaseResponseDto(
                            HttpStatus.NO_CONTENT,
                            "Book Deleted Successfully"
                    )
            );
        }
        return ResponseEntity.status(
                HttpStatus.EXPECTATION_FAILED
        ).body(
                new BaseResponseDto(
                        HttpStatus.EXPECTATION_FAILED,
                        "Book Deleted Unsuccessfully"
                )
        );
    }
    @PostMapping("/books/create/bulk")
    @Operation(
            summary = "Bulk Create Books",
            description = "This endpoint allows the creation of multiple books in a single request. If a book with the same title and author already exists, it will be excluded from creation. The request will return a list of successfully created books."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Books created successfully.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "No new books were created (books might already exist or invalid input data).",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    public ResponseEntity<BookResponseDto<List<BookDto>>> bulkCreateBooks(@RequestBody List<BookCreateOrUpdateDto> booksData) {

        List<BookDto> books = iBookService.bulkCreateBooks(booksData);


        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BookResponseDto<List<BookDto>>(
                            HttpStatus.BAD_REQUEST,
                            "No new books were created (books might already exist)",
                            books
                    ));
        }


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BookResponseDto<List<BookDto>>(
                        HttpStatus.CREATED,
                        "Books created successfully",
                        books
                ));
    }
    @GetMapping("/search")
    @Operation(
            summary = "Search Books",
            description = "Searches for books in the BookWiz platform by matching the provided text against the book title, author, or publisher."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved matching books.",
                    content = @Content(schema = @Schema(implementation = BookResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid search query provided.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    public ResponseEntity<BookResponseDto<List<BookDto>>> searchBooks(@RequestParam String q) {
        List<BookDto> books = iBookService.searchBooks(q);
        return ResponseEntity.status(
                HttpStatus.OK
        ).body(
                new BookResponseDto<List<BookDto>>(
                        HttpStatus.OK,
                        "Books retrieved successfully",
                        books
                )
        );
    }
}

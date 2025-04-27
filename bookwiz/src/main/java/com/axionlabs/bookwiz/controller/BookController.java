package com.axionlabs.bookwiz.controller;

import com.axionlabs.bookwiz.dto.BookDto;
import com.axionlabs.bookwiz.dto.ErrorResponseDto;
import com.axionlabs.bookwiz.service.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

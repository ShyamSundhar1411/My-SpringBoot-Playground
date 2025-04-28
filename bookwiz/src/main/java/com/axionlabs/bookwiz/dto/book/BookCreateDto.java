package com.axionlabs.bookwiz.dto.book;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookCreateDto {

    @NotEmpty(message = "Book Title cannot be null or empty")
    @Size(min = 5, message = "The length of the title of the book should be atleast 5")
    private String bookTitle;
    @NotEmpty(message = "Book Description cannot be null or empty")
    private String bookDescription;

    @NotEmpty(message = "Author Name cannot be null or empty")
    @Size(min = 3, message = "The length of author name should be atleast 3")
    private String author;

    @NotEmpty(message = "Genre cannot be null or empty")
    private String genre;

    private LocalDateTime publishedOn;

    private String publishedBy;
}


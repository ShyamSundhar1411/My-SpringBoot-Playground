package com.axionlabs.bookwiz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "book_description")
    private String bookDescription;
    private String author;
    private String genre;
    @Column(name = "published_on")
    private LocalDateTime publishedOn;
    @Column(name = "published_by")
    private String publishedBy;

}

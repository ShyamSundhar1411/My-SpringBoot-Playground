package com.axionlabs.bookwiz.mapper;

import com.axionlabs.bookwiz.dto.BookCreateDto;
import com.axionlabs.bookwiz.dto.BookDto;
import com.axionlabs.bookwiz.entity.Book;

public class BookMapper {
    public static BookDto mapToBookDto(BookDto bookDto, Book bookEntity){
        bookDto.setBookTitle(bookEntity.getBookTitle());
        bookDto.setBookId(bookEntity.getBookId());
        bookDto.setBookDescription(bookEntity.getBookDescription());
        bookDto.setAuthor(bookEntity.getAuthor());
        bookDto.setGenre(bookEntity.getGenre());
        bookDto.setPublishedBy(bookEntity.getPublishedBy());
        bookDto.setPublishedOn(bookEntity.getPublishedOn());
        return bookDto;
    }

    public static Book mapToBookEntity(BookDto bookDto, Book bookEntity){
        bookEntity.setBookTitle(bookDto.getBookTitle());
        bookEntity.setBookDescription(bookDto.getBookDescription());
        bookEntity.setAuthor(bookDto.getAuthor());
        bookEntity.setGenre(bookDto.getGenre());
        bookEntity.setPublishedBy(bookDto.getPublishedBy());
        bookEntity.setPublishedOn(bookDto.getPublishedOn());
        return bookEntity;
    }

    public static Book mapToBookEntity(BookCreateDto bookDto, Book bookEntity){
        bookEntity.setBookTitle(bookDto.getBookTitle());
        bookEntity.setBookDescription(bookDto.getBookDescription());
        bookEntity.setAuthor(bookDto.getAuthor());
        bookEntity.setGenre(bookDto.getGenre());
        bookEntity.setPublishedBy(bookDto.getPublishedBy());
        bookEntity.setPublishedOn(bookDto.getPublishedOn());
        return bookEntity;
    }
}

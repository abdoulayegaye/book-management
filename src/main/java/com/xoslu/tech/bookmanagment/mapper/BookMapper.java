package com.xoslu.tech.bookmanagment.mapper;

import com.xoslu.tech.bookmanagment.dto.BookRequestDTO;
import com.xoslu.tech.bookmanagment.dto.BookResponseDTO;
import com.xoslu.tech.bookmanagment.entity.Book;

public class BookMapper {

    public static BookResponseDTO bookToBookResponseDTO(Book book) {
        if (book == null) {
            return null;
        }
        BookResponseDTO bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setIsbn(book.getIsbn());
        bookResponseDTO.setPagesNumber(book.getPagesNumber());
        return bookResponseDTO;
    }

    public static Book bookRequestDTOToBook(BookRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPagesNumber(dto.getPagesNumber());
        book.setPublishedYear(dto.getPublishedYear());
        return book;
    }

    public static Book bookResponseDTOToBook(BookResponseDTO dto) {
        if (dto == null) {
            return null;
        }
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPagesNumber(dto.getPagesNumber());
        book.setPublishedYear(dto.getPublishedYear());
        return book;
    }
}

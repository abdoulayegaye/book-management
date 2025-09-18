package com.xoslu.tech.bookmanagment.service;

import com.xoslu.tech.bookmanagment.dto.BookRequestDTO;
import com.xoslu.tech.bookmanagment.dto.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(Long id);
    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);
    void deleteBookById(Long id);
    List<BookResponseDTO> getBooksByPublishedYear(int year);
    List<BookResponseDTO> getBooksByAuthor(String author);
    BookResponseDTO getBookByTitle(String title);
}

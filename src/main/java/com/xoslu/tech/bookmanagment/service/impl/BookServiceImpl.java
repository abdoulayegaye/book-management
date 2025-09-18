package com.xoslu.tech.bookmanagment.service.impl;

import com.xoslu.tech.bookmanagment.dto.BookRequestDTO;
import com.xoslu.tech.bookmanagment.dto.BookResponseDTO;
import com.xoslu.tech.bookmanagment.entity.Book;
import com.xoslu.tech.bookmanagment.mapper.BookMapper;
import com.xoslu.tech.bookmanagment.repository.BookRepository;
import com.xoslu.tech.bookmanagment.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = BookMapper.bookRequestDTOToBook(bookRequestDTO);
        book.setIsbn(UUID.randomUUID().toString());
        book = bookRepository.save(book);
        return BookMapper.bookToBookResponseDTO(book);
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(BookMapper::bookToBookResponseDTO).toList();
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        return bookRepository.findById(id).map(BookMapper::bookToBookResponseDTO).orElse(null);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        BookResponseDTO bookUpdated = getBookById(id);
        Book book = BookMapper.bookResponseDTOToBook(bookUpdated);
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        book.setPublishedYear(bookRequestDTO.getPublishedYear());
        book.setPagesNumber(bookRequestDTO.getPagesNumber());
        book = bookRepository.save(book);
        return BookMapper.bookToBookResponseDTO(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDTO> getBooksByPublishedYear(int year) {
        return bookRepository.findByPublishedYear(year).stream().map(BookMapper::bookToBookResponseDTO).toList();
    }

    @Override
    public List<BookResponseDTO> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author).stream().map(BookMapper::bookToBookResponseDTO).toList();
    }

    @Override
    public BookResponseDTO getBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        return BookMapper.bookToBookResponseDTO(book);
    }
}

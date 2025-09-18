package com.xoslu.tech.bookmanagment.controller;

import com.xoslu.tech.bookmanagment.dto.BookRequestDTO;
import com.xoslu.tech.bookmanagment.dto.BookResponseDTO;
import com.xoslu.tech.bookmanagment.entity.Book;
import com.xoslu.tech.bookmanagment.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Livres", description = "Gestion des livres")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Lister tous les livres", description = "Liste tous les livres de la BD")
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    @Operation(summary = "Ajouter un nouveau livre", description = "Ajoute et sauvegarde un nouveau livre dans la BD")
    public BookResponseDTO createBook(@RequestBody BookRequestDTO book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un livre", description = "Récupérer un livre de la BD via son ID")
    public BookResponseDTO getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un livre", description = "Modifie un livre de la BD via son ID")
    public BookResponseDTO updateBook(@PathVariable("id") Long id, @RequestBody BookRequestDTO book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un livre", description = "Supprime un livre de la BD via son ID")
    public void deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping("/published-year")
    @Operation(
            summary = "Lister les livres de la même année de publication",
            description = "Liste tous les livres de la même année de publication de la BD"
    )
    public List<BookResponseDTO> getBooksByPublishedYear(@RequestParam("published_year") int publishedYear) {
        return bookService.getBooksByPublishedYear(publishedYear);
    }

    @GetMapping("/author")
    @Operation(
            summary = "Lister les livres du même auteur",
            description = "Liste tous les livres du même auteur de la BD"
    )
    public List<BookResponseDTO> getBooksByAuthor(@RequestParam("author") String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/title")
    @Operation(
            summary = "Récupérer un livre",
            description = "Récupére un livre de la BD via son titre"
    )
    public BookResponseDTO getBooksByTitle(@RequestParam("title") String title) {
        return bookService.getBookByTitle(title);
    }
}

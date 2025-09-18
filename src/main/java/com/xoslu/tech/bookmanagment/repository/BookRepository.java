package com.xoslu.tech.bookmanagment.repository;

import com.xoslu.tech.bookmanagment.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByPublishedYear(int publishedYear);
}

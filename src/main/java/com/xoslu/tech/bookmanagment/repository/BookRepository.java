package com.xoslu.tech.bookmanagment.repository;

import com.xoslu.tech.bookmanagment.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

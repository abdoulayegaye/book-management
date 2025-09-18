package com.xoslu.tech.bookmanagment;

import com.xoslu.tech.bookmanagment.entity.Book;
import com.xoslu.tech.bookmanagment.repository.BookRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "API de gestion des livres",
                version = "1.0",
                description = "Documentation Swagger pour notre API Spring Boot"
        )
)
public class BookManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookManagmentApplication.class, args);
    }

   /*@Bean
    CommandLineRunner initDatabase(BookRepository bookRepository) {
        return args -> {
            bookRepository.save(
                    Book.builder()
                            .title("Book 1")
                            .author("Author 1")
                            .isbn(UUID.randomUUID().toString())
                            .pagesNumber(33)
                            .build()
            );

            bookRepository.save(
                    Book.builder()
                            .title("Book 2")
                            .author("Author 2")
                            .isbn(UUID.randomUUID().toString())
                            .publishedYear(2018)
                            .pagesNumber(45)
                            .build()
            );

            bookRepository.save(
                    Book.builder()
                            .title("Book 3")
                            .author("Author 3")
                            .isbn(UUID.randomUUID().toString())
                            .pagesNumber(36)
                            .build()
            );
        };
    }*/
}

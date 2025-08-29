package com.xoslu.tech.bookmanagment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String title;
    @Column(nullable = false, length = 100)
    private String author;
    @Column(length = 100)
    private String isbn; //International Standard Book Number
    private Integer publishedYear;
    private int pagesNumber;
}

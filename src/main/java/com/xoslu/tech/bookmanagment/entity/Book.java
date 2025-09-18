package com.xoslu.tech.bookmanagment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 100)
    private String title;
    @Column(nullable = false, length = 100)
    private String author;
    @Column(length = 100, updatable = false)
    private String isbn; //International Standard Book Number
    @JsonProperty("published_year")
    private Integer publishedYear;
    @JsonProperty("pages_number")
    private int pagesNumber;
}

package com.xoslu.tech.bookmanagment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    private String title;
    private String author;
    @JsonProperty("published_year")
    private Integer publishedYear;
    @JsonProperty("pages_number")
    private int pagesNumber;
}

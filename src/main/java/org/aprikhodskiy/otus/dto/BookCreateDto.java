package org.aprikhodskiy.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCreateDto {
    private String name;
    private Long genreId;
    private Long authorId;
}

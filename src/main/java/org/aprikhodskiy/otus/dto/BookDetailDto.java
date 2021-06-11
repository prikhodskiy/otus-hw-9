package org.aprikhodskiy.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class BookDetailDto {
    private long id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;
    private List<String> comments;
}

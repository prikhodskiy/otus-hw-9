package org.aprikhodskiy.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@AllArgsConstructor
@Getter
public class BookDto {
    private long id;
    private String name;
    private String authorName;
    private String genre;
}

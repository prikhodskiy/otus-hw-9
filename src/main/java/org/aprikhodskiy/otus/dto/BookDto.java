package org.aprikhodskiy.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.aprikhodskiy.otus.models.Book;


@Builder
@AllArgsConstructor
@Getter
public class BookDto {
    private long id;
    private String name;
    private String authorName;
    private String genre;

    public static BookDto toDto(Book book){
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .authorName(book.getAuthor().getName())
                .genre(book.getGenre().getName())
                .build();
    }
}

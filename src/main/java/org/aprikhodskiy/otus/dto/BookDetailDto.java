package org.aprikhodskiy.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.aprikhodskiy.otus.models.Book;
import org.aprikhodskiy.otus.models.Comment;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class BookDetailDto {
    private long id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;
    private List<String> comments;

    public static BookDetailDto toDto(Book book) {
        return BookDetailDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(new AuthorDto(book.getAuthor().getId(), book.getAuthor().getName()))
                .genre(new GenreDto(book.getGenre().getId(), book.getGenre().getName()))
                .comments(Objects.isNull(book.getComments()) ? null :
                        book.getComments().stream().map(Comment::getText).collect(Collectors.toList()
                        ))
                .build();
    }
}

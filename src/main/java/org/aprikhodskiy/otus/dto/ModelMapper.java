package org.aprikhodskiy.otus.dto;

import org.aprikhodskiy.otus.models.Author;
import org.aprikhodskiy.otus.models.Book;
import org.aprikhodskiy.otus.models.Comment;
import org.aprikhodskiy.otus.models.Genre;

import java.util.Objects;
import java.util.stream.Collectors;

public class ModelMapper {

    private ModelMapper() {
        throw new AssertionError();
    }

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    public static BookDto toDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .authorName(book.getAuthor().getName())
                .genre(book.getGenre().getName())
                .build();
    }

    public static BookDetailDto toDetailDto(Book book) {
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

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }
}

package org.aprikhodskiy.otus.dao;

import org.aprikhodskiy.otus.domain.Author;
import org.aprikhodskiy.otus.domain.Book;
import org.aprikhodskiy.otus.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    @Autowired
    BookDaoJdbc bookDaoJdbc;

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void getAll() {
        List<Book> books = bookDaoJdbc.getAll();
        assertNotNull(books);
        assertThat(books.size()).isGreaterThan(2);
    }

    @DisplayName("добавлять новую книгу в БД")
    @Test
    void insert() {
        Book bookBeforeInsert = new Book(100500, "hello from unit test", new Author(3, "Vasily Pupkin"), new Genre(1, "Short stories"));
        bookDaoJdbc.insert(bookBeforeInsert);
        Book bookAfterInsert = bookDaoJdbc.getById(bookBeforeInsert.getId());
        assertThat(bookBeforeInsert).usingRecursiveComparison().isEqualTo(bookAfterInsert);
    }

    @DisplayName("изменять название книги")
    @Test
    void update() {
        Book bookBeforeUpdate = bookDaoJdbc.getById(77);
        assertEquals("This will be updated by test", bookBeforeUpdate.getName());

        Book book = new Book(77, "nameAfterUpdate", bookBeforeUpdate.getAuthor(), bookBeforeUpdate.getGenre());
        bookDaoJdbc.update(book);

        Book bookAfterUpdate = bookDaoJdbc.getById(77);
        assertEquals("nameAfterUpdate", bookAfterUpdate.getName());
    }

    @DisplayName("возвращать книги по id вместе с жанром и автором")
    @Test
    void getById() {
        Book book = bookDaoJdbc.getById(2);
        assertNotNull(book);
        assertThat(book.getId()).isEqualTo(2);
        assertNotNull(book.getAuthor());
        assertNotNull(book.getGenre());
    }

    @DisplayName("возвращать книги по id автора")
    @Test
    void getByAuthorId() {
        List<Book> books = bookDaoJdbc.getByAuthorId(2l);
        assertNotNull(books);
        assertThat(books).hasSize(2);
        assertThat(books).extractingResultOf("getAuthor").extractingResultOf("getId").containsOnly(2l);
    }

    @DisplayName("возвращать книги по id жанра")
    @Test
    void getByGenreId() {
        List<Book> books = bookDaoJdbc.getByGenreId(3l);
        assertNotNull(books);
        assertThat(books).hasSize(3);
        assertThat(books).extractingResultOf("getGenre").extractingResultOf("getId").containsOnly(3l);
    }

    @DisplayName("удалять книгу")
    @Test
    void deleteById() {
        assertThatCode(() -> bookDaoJdbc.getById(13))
                .doesNotThrowAnyException();

        bookDaoJdbc.deleteById(13);

        assertThatThrownBy(() -> bookDaoJdbc.getById(13))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }
}
package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Author;
import org.aprikhodskiy.otus.models.Book;
import org.aprikhodskiy.otus.models.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Репозиторий на основе Jpa для работы с книгами")
@DataJpaTest
@Import(BookRepositoryJpaImpl.class)
class BookRepositoryJpaImplTest {

    @Autowired
    BookRepositoryJpaImpl repositoryJpa;

    @Autowired
    TestEntityManager em;

    @DisplayName("должен загружать список всех книг ")
    @Test
    void shouldReturnCorrectBooksList() {
        var books = repositoryJpa.findAll();
        assertNotNull(books);
        assertThat(books.size()).isGreaterThan(5);
    }

    @DisplayName("должен загружать всю информацию о книге  ")
    @Test
    void shouldReturnCorrectBookById() {
        var book = repositoryJpa.findById(3).orElse(null);

        assertNotNull(book);
        assertThat(book.getAuthor()).isEqualTo(getDostoevsky());

        assertNotNull(book.getGenre());
        assertThat(book.getGenre().getId()).isEqualTo(3);
    }

    @DisplayName("должен загружать список всех книг по автору ")
    @Test
    void shouldReturnCorrectBooksListByAuthorId() {
        var books = repositoryJpa.findByAuthorId(getDostoevsky().getId());
        assertNotNull(books);
        assertThat(books.size()).isEqualTo(2);
        assertThat(books).filteredOn("Author", getDostoevsky()).size().isEqualTo(2);
    }

    @DisplayName("должен загружать список всех книг по жанру ")
    @Test
    void shouldReturnCorrectBooksListByGenreId() {
        var books = repositoryJpa.findByGenreId(getNovel().getId());
        assertNotNull(books);
        assertThat(books.size()).isEqualTo(3);
        assertThat(books).filteredOn("Genre", getNovel()).size().isEqualTo(3);
    }

    @DisplayName("должен уметь добавлять книгу ")
    @Test
    void shouldAddNewBook() {
        int initialCount = repositoryJpa.findAll().size();

        Book newBook = Book.builder()
                .name("Some name")
                .author(getDostoevsky())
                .genre(getNovel())
                .build();

        repositoryJpa.save(newBook);

        assertThat(initialCount + 1).isEqualTo(repositoryJpa.findAll().size());
    }

    @DisplayName("должен уметь обновлять книгу ")
    @Test
    void shouldUpdateBook() {
        Book bookBeforeUpdate = repositoryJpa.findById(77).orElse(null);
        assertEquals("This will be updated by test", bookBeforeUpdate.getName());
        bookBeforeUpdate.setName("nameAfterUpdate");
        repositoryJpa.save(bookBeforeUpdate);
        em.flush();
        Book bookAfterUpdate = repositoryJpa.findById(77).orElse(null);
        assertEquals("nameAfterUpdate", bookAfterUpdate.getName());
    }

    @DisplayName("должен уметь удалять книгу")
    @Test
    void shouldDeleteById() {
        assertThatCode(() -> repositoryJpa.findById(13))
                .doesNotThrowAnyException();
        int initialCount = repositoryJpa.findAll().size();

        repositoryJpa.deleteById(13);
        em.flush();

        assertThat(initialCount - 1).isEqualTo(repositoryJpa.findAll().size());
    }


    @DisplayName("должен загружать список комментариев по книге")
    @Test
    void shouldReturnCorrectCommentsListByBook() {
        var comments = repositoryJpa.findById(5L).orElseThrow().getComments();
        assertNotNull(comments);
        assertThat(comments.size()).isEqualTo(3);
    }

    Author getDostoevsky() {
        return new Author(2, "Fyodor Dostoevsky");
    }

    Genre getNovel() {
        return new Genre(3, "Novel");
    }
}
package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryJpa {
    List<Book> findAll();

    Book save(Book book);

    Optional<Book> findById(long id);

    void deleteById(long id);

    List<Book> findByAuthorId(Long authorId);

    List<Book> findByGenreId(Long genreId);
}

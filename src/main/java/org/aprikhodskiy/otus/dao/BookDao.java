package org.aprikhodskiy.otus.dao;

import org.aprikhodskiy.otus.domain.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();

    void insert(Book book);

    void update(Book book);

    Book getById(long id);

    void deleteById(long id);

    List<Book> getByAuthorId(Long authorId);

    List<Book> getByGenreId(Long genreId);
}

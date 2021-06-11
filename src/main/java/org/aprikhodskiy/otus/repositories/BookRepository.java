package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findByAuthorId(Long authorId);
    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findByGenreId(Long genreId);
    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findAll();
}

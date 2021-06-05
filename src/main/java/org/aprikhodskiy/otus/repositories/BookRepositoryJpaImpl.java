package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b ",
                Book.class);

        setGraphHint(query);
        return query.getResultList();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> findByAuthorId(Long authorId) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.author.id = :author_id",
                Book.class);
        query.setParameter("author_id", authorId);
        setGraphHint(query);
        return query.getResultList();
    }

    @Override
    public List<Book> findByGenreId(Long genreId) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.genre.id = :genre_id",
                Book.class);
        query.setParameter("genre_id", genreId);
        setGraphHint(query);
        return query.getResultList();
    }

    private void setGraphHint(TypedQuery<Book> query) {
        EntityGraph entityGraph = em.getEntityGraph("books-entity-graph");
        query.setHint("javax.persistence.fetchgraph", entityGraph);
    }
}

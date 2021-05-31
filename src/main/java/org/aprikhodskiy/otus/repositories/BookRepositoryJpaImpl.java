package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class BookRepositoryJpaImpl implements BookRepositoryJpa {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b " +
                        "join fetch b.author " +
                        "join fetch b.genre",
                Book.class);
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
                "select b from Book b " +
                        "join fetch b.author " +
                        "join fetch b.genre " +
                        "where b.author.id = :author_id",
                Book.class);
        query.setParameter("author_id", authorId);
        return query.getResultList();
    }

    @Override
    public List<Book> findByGenreId(Long genreId) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b " +
                        "join fetch b.author " +
                        "join fetch b.genre " +
                        "where b.genre.id = :genre_id",
                Book.class);
        query.setParameter("genre_id", genreId);
        return query.getResultList();
    }
}

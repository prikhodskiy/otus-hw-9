package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepositoryJpa {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select a from Genre a", Genre.class).getResultList();
    }
}

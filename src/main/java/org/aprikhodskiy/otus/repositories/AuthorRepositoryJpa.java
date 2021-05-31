package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Author;

import java.util.List;

public interface AuthorRepositoryJpa {
    List<Author> findAll();
}

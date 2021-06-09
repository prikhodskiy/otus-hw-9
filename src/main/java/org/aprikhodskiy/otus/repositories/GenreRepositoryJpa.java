package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Genre;

import java.util.List;

public interface GenreRepositoryJpa {
    List<Genre> findAll();
}

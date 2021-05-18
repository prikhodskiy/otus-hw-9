package org.aprikhodskiy.otus.dao;

import org.aprikhodskiy.otus.domain.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();
}

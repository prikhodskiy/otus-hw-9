package org.aprikhodskiy.otus.dao;

import org.aprikhodskiy.otus.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getAll();
}

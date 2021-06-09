package org.aprikhodskiy.otus.repositories;

import org.aprikhodskiy.otus.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepositoryJpa {
    List<Comment> findAll();

    Comment save(Comment book);

    Optional<Comment> findById(long id);

    void deleteById(long id);
}

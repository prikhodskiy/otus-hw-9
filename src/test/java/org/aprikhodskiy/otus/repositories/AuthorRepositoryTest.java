package org.aprikhodskiy.otus.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с авторами ")
@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repositoryJpa;

    @DisplayName("должен загружать список всех авторов ")
    @Test
    void shouldReturnCorrectAuthorsList() {
        var authors = repositoryJpa.findAll();
        assertNotNull(authors);
        assertThat(authors.size()).isGreaterThan(2);
    }
}
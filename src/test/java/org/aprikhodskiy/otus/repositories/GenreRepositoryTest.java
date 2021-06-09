package org.aprikhodskiy.otus.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий на основе Jpa для работы с жанрами")
@DataJpaTest
class GenreRepositoryTest {
    @Autowired
    GenreRepository repositoryJpa;

    @DisplayName("должен загружать список всех жанров ")
    @Test
    void shouldReturnCorrectAuthorsList() {
        var genres = repositoryJpa.findAll();
        assertNotNull(genres);
        assertThat(genres.size()).isGreaterThan(2);
    }

}
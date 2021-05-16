package org.aprikhodskiy.otus.dao;

import org.aprikhodskiy.otus.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedGenreList() {
        List<Genre> genres = genreDaoJdbc.getAll();
        assertThat(genres).hasSize(3);
    }

    @DisplayName("содержать жанр Sci-Fi")
    @Test
    void shouldReturnSciFi() {
        List<Genre> genres = genreDaoJdbc.getAll();
        assertThat(genres).filteredOn(g -> g.getName().equals("Sci-Fi")).hasSize(1);
    }
}